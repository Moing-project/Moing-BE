package com.moing.moingbe.domain.task.repository;

import com.moing.moingbe.domain.task.dao.TaskKanbanDao;
import com.moing.moingbe.domain.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QTaskRepository {

    List<TaskKanbanDao> getListTaskKanban(List<Long> taskList);

    Long finalTask(Long workId);

    Page<Task> taskList(Long workId, Pageable pageable);
}
