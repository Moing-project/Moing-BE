package com.moing.moingbe.domain.workspace.service;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;
import com.moing.moingbe.domain.auth.repository.AuthRepository;
import com.moing.moingbe.domain.workspace.dao.WorkMainDao;
import com.moing.moingbe.domain.workspace.dto.WorkCreateRequestDto;
import com.moing.moingbe.domain.workspace.dto.WorkListResponseDto;
import com.moing.moingbe.domain.workspace.dto.WorkResponseDto;
import com.moing.moingbe.domain.workspace.entity.WorkStack;
import com.moing.moingbe.domain.workspace.entity.WorkTeam;
import com.moing.moingbe.domain.workspace.entity.Workspace;
import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import com.moing.moingbe.domain.workspace.repository.WorkRepository;
import com.moing.moingbe.domain.workspace.repository.WorkStackRepository;
import com.moing.moingbe.domain.workspace.repository.WorkTeamRepository;
import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.dto.MessageResponseDto;
import com.moing.moingbe.global.enums.AuthGlobalUserEnum;
import com.moing.moingbe.global.exception.ErrorCodeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.moing.moingbe.global.enums.AccessCode.WORKSPACE_CREATE_ALLOW;
import static com.moing.moingbe.global.enums.DeniedCode.WORKSPACE_CREATE_ERROR;
import static com.moing.moingbe.global.enums.DeniedCode.WORKSPACE_NOT_FOUND_ERROR;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final AuthRepository authRepository;
    private final WorkServiceHelper workServiceHelper;
    private final WorkTeamRepository workTeamRepository;
    private final WorkStackRepository workStackRepository;


    public WorkService(WorkRepository workRepository, AuthRepository authRepository, WorkServiceHelper workServiceHelper, WorkTeamRepository workTeamRepository, WorkStackRepository workStackRepository) {
        this.workRepository = workRepository;
        this.authRepository = authRepository;
        this.workServiceHelper = workServiceHelper;
        this.workTeamRepository = workTeamRepository;
        this.workStackRepository = workStackRepository;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public MessageResponseDto createWorkspace(WorkCreateRequestDto createDto, Long userId, AuthGlobalUserEnum userRating) {
        Workspace newWorkspace = new Workspace(createDto, workServiceHelper.createMaxMemberToUserRating(userRating));

        newWorkspace = workRepository.save(newWorkspace);

        if (!workspaceAddUser(newWorkspace.getId(), userId))
            throw ErrorCodeException.make(WORKSPACE_CREATE_ERROR.status(), WORKSPACE_CREATE_ERROR.code());
        if (!workspaceAddStacks(newWorkspace.getId(), createDto.stacks()))
            throw ErrorCodeException.make(WORKSPACE_CREATE_ERROR.status(), WORKSPACE_CREATE_ERROR.code());
        return MessageResponseDto.out(WORKSPACE_CREATE_ALLOW.status(), WORKSPACE_CREATE_ALLOW.code());
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public BaseResponseDto<Workspace> createWorkspaceDebug(WorkCreateRequestDto createDto, Long userId, AuthGlobalUserEnum userRating) {
        Workspace newWorkspace = new Workspace(createDto, workServiceHelper.createMaxMemberToUserRating(userRating));

        newWorkspace = workRepository.save(newWorkspace);

        if (!workspaceAddUser(newWorkspace.getId(), userId)) {
            throw ErrorCodeException.make(WORKSPACE_CREATE_ERROR.status(), WORKSPACE_CREATE_ERROR.code());
        }
        if (!workspaceAddStacks(newWorkspace.getId(), createDto.stacks())) {
            throw ErrorCodeException.make(WORKSPACE_CREATE_ERROR.status(), WORKSPACE_CREATE_ERROR.code());
        }
        return BaseResponseDto.builder(newWorkspace).status(WORKSPACE_CREATE_ALLOW.status()).msg(WORKSPACE_CREATE_ALLOW.code()).build();
    }

    public List<WorkListResponseDto> getWorkspaces(Long userId) {
        List<WorkTeam> works = workTeamRepository.findAllByUserIdOrderByModifiedAtDesc(userId);
        List<WorkMainDao> workspaces = workRepository.findAllByListWorkIdToMainDao(works);
        List<WorkListResponseDto> result = new ArrayList<>();
        for (WorkTeam work : works)
            for (WorkMainDao workspace : workspaces)
                if (Objects.equals(work.getWorkId(), workspace.id())) {
                    result.add(new WorkListResponseDto(workspace.id(), workspace.imageSrc(), workspace.title(), work.isFavorite()));
                    workspaces.remove(workspace);
                    break;
                }
        return result;
    }

    public WorkResponseDto getWorkspace(Long workId) {
        Workspace workspace = workRepository.findById(workId).orElseThrow(() -> ErrorCodeException.make(WORKSPACE_NOT_FOUND_ERROR.status(), WORKSPACE_NOT_FOUND_ERROR.code()));
        return new WorkResponseDto(workspace, workStackRepository.findAllByWorkIdToStack(workId), getWorkUserList(workId));
    }

    private boolean workspaceAddUser(Long workspaceId, Long userId) {
        if (workTeamRepository.findByWorkIdAndUserId(workspaceId, userId).isPresent()) {
            return false;
        }
        workTeamRepository.save(new WorkTeam(workspaceId, userId));
        return true;
    }

    private boolean workspaceAddStacks(Long workspaceId, List<WorkStackEnum> stacks) {
        List<WorkStackEnum> workStackEnumList = new ArrayList<>();
        for (WorkStackEnum stack : stacks) {
            if (workStackRepository.findByWorkIdAndStack(workspaceId, stack).isEmpty()) {
                workStackRepository.save(new WorkStack(workspaceId, stack));
            }
        }
        return true;
    }

    private List<AuthWorkSoloResponseDto> getWorkUserList(Long workspaceId) {
        List<AuthWorkSoloResponseDto> result = new ArrayList<>();
        for (Long userId : workTeamRepository.findAllSelectUserIdByWorkId(workspaceId)) {
            result.add(new AuthWorkSoloResponseDto(authRepository.findByProfileImageAndNicknameById(userId)));
        }
        return result;
    }

    private WorkListResponseDto getListResponseDto(Workspace workspace, boolean favorite) {
        return new WorkListResponseDto(workspace.getId(),
                workspace.getImageSrc(),
                workspace.getTitle(),
                favorite);
    }
}
