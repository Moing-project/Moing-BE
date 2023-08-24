package com.moing.moingbe.domain.task.repository;

import com.moing.moingbe.domain.task.dao.TaskKanbanDao;

import java.util.List;

public interface QTaskRepository {

    List<TaskKanbanDao> getListTaskKanban(List<Long> taskList);
}
