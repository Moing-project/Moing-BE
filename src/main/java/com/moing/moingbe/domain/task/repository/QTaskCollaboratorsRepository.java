package com.moing.moingbe.domain.task.repository;

import java.util.List;

public interface QTaskCollaboratorsRepository {

    List<Long> findAllByTaskListByUserId(Long userId);
}
