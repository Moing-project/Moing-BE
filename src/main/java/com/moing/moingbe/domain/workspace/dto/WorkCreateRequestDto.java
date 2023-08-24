package com.moing.moingbe.domain.workspace.dto;

import com.moing.moingbe.domain.workspace.enums.WorkSubjectEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "워크스페이스 제작")
public record WorkCreateRequestDto(
        String title,
        
        String subject,
        Integer needMember,
        String date,
        String allowType,
        List<String> stacks,
        String introduce,
        String imageSrc) {
}
