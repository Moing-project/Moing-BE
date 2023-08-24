package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;

import java.util.List;

public interface QWorkStackRepository {

    List<WorkStackEnum> findAllByWorkIdToStack(Long workId);
}
