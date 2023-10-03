package com.moing.moingbe.domain.task.dto;

import com.moing.moingbe.domain.task.enums.TaskCategoryEnum;
import com.moing.moingbe.domain.task.enums.TaskStatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TaskCreateRequestDto {

    private Long workId;

    private String title;

    // Kanban
    private TaskStatusEnum taskStatus;
    private TaskCategoryEnum category;

    private String description;
    private Boolean disclosure;

    // Calendar
    private LocalDateTime startTime;
    private LocalDateTime lastTime;

    private List<Long> userIds;

    public TaskCreateRequestDto(Long workId, String title, TaskStatusEnum taskStatus, TaskCategoryEnum category, String description, Boolean disclosure, LocalDateTime startTime, LocalDateTime lastTime, List<Long> userIds) {
        this.workId = workId;
        this.title = title;
        this.taskStatus = taskStatus;
        this.category = category;
        this.description = description;
        this.disclosure = disclosure;
        this.startTime = startTime;
        this.lastTime = lastTime;
        this.userIds = userIds;
    }
}
