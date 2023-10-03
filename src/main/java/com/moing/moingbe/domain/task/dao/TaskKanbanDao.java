package com.moing.moingbe.domain.task.dao;

import com.moing.moingbe.domain.task.enums.TaskStatusEnum;
import lombok.Getter;

@Getter
public class TaskKanbanDao {

    private final Long id;

    private final String title;

    private final String description;

    private final TaskStatusEnum status;

    public TaskKanbanDao(Long id, String title, String description, TaskStatusEnum status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
