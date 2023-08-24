package com.moing.moingbe.domain.task.repository;

import com.moing.moingbe.domain.task.entity.TaskCollaborators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCollaboratorsRepository extends JpaRepository<TaskCollaborators, Long>, QTaskCollaboratorsRepository{
}
