package com.moing.moingbe.domain.team.service;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;
import com.moing.moingbe.domain.auth.repository.AuthRepository;
import com.moing.moingbe.domain.team.dto.TeamMainResponseDto;
import com.moing.moingbe.domain.team.dto.TeamOneResponseDto;
import com.moing.moingbe.domain.workspace.entity.Workspace;
import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import com.moing.moingbe.domain.workspace.repository.WorkRepository;
import com.moing.moingbe.domain.workspace.repository.WorkStackRepository;
import com.moing.moingbe.domain.workspace.repository.WorkTeamRepository;
import com.moing.moingbe.global.enums.DeniedCode;
import com.moing.moingbe.global.exception.DeniedCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TeamService {
    private final AuthRepository authRepository;
    private final WorkRepository workRepository;
    private final WorkTeamRepository workTeamRepository;
    private final WorkStackRepository workStackRepository;


    public TeamService(AuthRepository authRepository, WorkRepository workRepository, WorkTeamRepository workTeamRepository, WorkStackRepository workStackRepository) {
        this.authRepository = authRepository;
        this.workRepository = workRepository;
        this.workTeamRepository = workTeamRepository;
        this.workStackRepository = workStackRepository;
    }

    public List<TeamMainResponseDto> getTeamMain() {
        List<Workspace> workspaceList = workRepository.findAllOrderByAllowTypeToSort();
        List<TeamMainResponseDto> responseDtoList = new ArrayList<>();
        for (Workspace workspace : workspaceList) {
            responseDtoList.add(new TeamMainResponseDto(workspace.getId(), workspace.getImageSrc(), workspace.getTitle(),workStackRepository.findAllByWorkIdToStack(workspace.getId()),workspace.getAllowType(), workspace.getIntroduce(),workspace.getLastTime()));
        }
        return responseDtoList;
    }

    public TeamOneResponseDto getTeamOne(Long workId) {
        Workspace workspace = workRepository.findById(workId).orElseThrow(() -> DeniedCodeException.out(DeniedCode.WORKSPACE_NOT_FOUND_ERROR));
        List<AuthWorkSoloResponseDto> authWorkSoloResponseDtoList = workTeamRepository
                .findAllSelectUserIdByWorkId(workId)
                .stream()
                .map(authRepository::findByProfileImageAndNicknameById)
                .map(AuthWorkSoloResponseDto::new)
                .toList();
        List<String> stacks = workStackRepository.findAllByWorkIdToStack(workId).stream().map(WorkStackEnum::getStack).toList();
        return new TeamOneResponseDto(
                workspace.getImageSrc(),
                workspace.getAllowType().getType(),
                workspace.getLastTime(),
                workspace.getTotalMember(),
                authWorkSoloResponseDtoList,
                workspace.getIntroduce(),
                stacks);
    }
}
