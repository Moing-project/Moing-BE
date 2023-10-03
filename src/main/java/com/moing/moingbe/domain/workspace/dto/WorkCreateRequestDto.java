package com.moing.moingbe.domain.workspace.dto;

import com.moing.moingbe.domain.workspace.enums.WorkAllowEnum;
import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import com.moing.moingbe.domain.workspace.enums.WorkSubjectEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "워크스페이스 제작")
public record WorkCreateRequestDto(
        String title,
        WorkSubjectEnum subject,
        Integer needMember,
        String date,
        WorkAllowEnum allowType,
        List<WorkStackEnum> stacks,
        String introduce,
        String imageSrc) {
}
