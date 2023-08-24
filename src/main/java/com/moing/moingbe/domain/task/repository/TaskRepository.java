package com.moing.moingbe.domain.task.repository;

import com.moing.moingbe.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
