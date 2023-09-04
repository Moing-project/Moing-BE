package com.moing.moingbe.domain.task.entity;

import com.moing.moingbe.domain.task.dto.TaskCreateRequestDto;
import com.moing.moingbe.domain.task.enums.TaskCategoryEnum;
import com.moing.moingbe.domain.task.enums.TaskStatusEnum;
import com.moing.moingbe.global.maps.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Task extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long workId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean disclosure;

    // Kanban-board
    private TaskStatusEnum statusEnum;

    private TaskCategoryEnum categoryEnum;

    // Calendar
    private LocalDateTime startTime;

    private LocalDateTime lastTime;

    public Task(Long taskId, TaskCreateRequestDto requestDto) {
        this(taskId,
                requestDto.getWorkId(),
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getDisclosure(),
                requestDto.getTaskStatus(),
                requestDto.getCategory(),
                requestDto.getStartTime(),
                requestDto.getLastTime());
    }

    public Task() {

    }

    public Task(Long taskId, Long workId, String title, String description, boolean disclosure, TaskStatusEnum statusEnum, TaskCategoryEnum categoryEnum, LocalDateTime startTime, LocalDateTime lastTime) {
        this.taskId = taskId;
        this.workId = workId;
        this.title = title;
        this.description = description;
        this.disclosure = disclosure;
        this.statusEnum = statusEnum;
        this.categoryEnum = categoryEnum;
        this.startTime = startTime;
        this.lastTime = lastTime;
    }
}
