package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.dao.WorkMainDao;
import com.moing.moingbe.domain.workspace.entity.WorkTeam;
import com.moing.moingbe.domain.workspace.entity.Workspace;

import java.util.List;

public interface QWorkRepository {
    List<Workspace> findAllOrderByAllowType();
    List<Workspace> findAllOrderByAllowTypeToSort();

    List<WorkMainDao> findAllByListWorkIdToMainDao(List<WorkTeam> workList);
}
