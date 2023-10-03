package com.moing.moingbe.domain.team.dto;

import com.moing.moingbe.domain.workspace.enums.WorkAllowEnum;
import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;

import java.time.LocalDateTime;
import java.util.List;

public record TeamMainResponseDto (
        Long id,
        String imageSrc,
        String name,
        List<WorkStackEnum> stacks,
        WorkAllowEnum allowType,
        String introduce,
        LocalDateTime endTime
){

}
