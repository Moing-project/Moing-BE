package com.moing.moingbe.domain.team.dto;

import com.moing.moingbe.domain.auth.dto.AuthWorkSoloResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record TeamOneResponseDto(
        String ImageSrc,
        String allowType,
        LocalDateTime lastTime,
        Integer needMember,
        List<AuthWorkSoloResponseDto> contribution,
        String introduce,
        List<String> stacks) {
}
