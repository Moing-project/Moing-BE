package com.moing.moingbe.domain.kanban.controller;

import com.moing.moingbe.domain.kanban.service.KanbanService;
import com.moing.moingbe.global.dto.BaseResponseDto;
import com.moing.moingbe.global.sercurity.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kanban")
public class KanbanController {

    private final KanbanService kanbanService;

    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    public ResponseEntity<BaseResponseDto<?>> getMainKanban(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(kanbanService.getMainKanban(userDetails.getUser().getId()));

    }
}
