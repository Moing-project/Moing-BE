package com.moing.moingbe.domain.task.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TaskCollaborators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long userId;


    public TaskCollaborators(Long taskId, Long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public TaskCollaborators() {

    }
}
