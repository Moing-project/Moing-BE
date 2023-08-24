package com.moing.moingbe.domain.kanban.service;

import com.moing.moingbe.global.dto.BaseResponseDto;
import org.springframework.stereotype.Service;

@Service
public class KanbanService {
    public BaseResponseDto<?> getMainKanban(Long id) {



        return BaseResponseDto.builder(id).build();
    }
}
