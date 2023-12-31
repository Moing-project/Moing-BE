package com.moing.moingbe.domain.workspace.entity;

import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class WorkStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long workId;

    @Enumerated(value = EnumType.STRING)
    WorkStackEnum stack;

    public WorkStack() {
    }

    public WorkStack(Long workId, WorkStackEnum stacks) {
        this.workId = workId;
        this.stack = stacks;
    }
}
