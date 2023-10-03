package com.moing.moingbe.domain.workspace.controller;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;
import com.moing.moingbe.domain.workspace.dto.WorkCreateRequestDto;
import com.moing.moingbe.domain.workspace.dto.WorkListResponseDto;
import com.moing.moingbe.domain.workspace.dto.WorkResponseDto;
import com.moing.moingbe.domain.workspace.entity.Workspace;
import com.moing.moingbe.domain.workspace.service.WorkService;
import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.sercurity.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }


//    @PostMapping("/create")
//    public ResponseEntity<MessageResponseDto> createWorkspace(@RequestBody WorkCreateRequestDto createDto,
//                                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
//        Long userId = userDetails.getUser().getId();
//        return ResponseEntity.status(HttpStatus.CREATED.value()).body(workService.createWorkspace(createDto, userId, userDetails.getUser().getUserRole()));
//    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto<Workspace>> createWorkspaceDebug(@RequestBody WorkCreateRequestDto createDto,
                                                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(workService.createWorkspaceDebug(createDto, userId, userDetails.getUser().getUserRole()));
    }

    @GetMapping("/main")
    public ResponseEntity<List<WorkListResponseDto>> getWorkspaces(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(workService.getWorkspaces(userDetails.getUser().getId()));
    }

    @GetMapping("/{workId}")
    public ResponseEntity<WorkResponseDto> getWorkspace(@PathVariable Long workId){
        return ResponseEntity.ok(workService.getWorkspace(workId));
    }

    @GetMapping("/{workId}/user")
    public ResponseEntity<List<AuthWorkSoloResponseDto>> getWorkUserList(@PathVariable Long workId){
        return ResponseEntity.ok(workService.getWorkUserList(workId));
    }
}
