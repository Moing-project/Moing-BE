package com.moing.moingbe.domain.task.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.moing.moingbe.domain.task.entity.QTaskCollaborators.taskCollaborators;

public class QTaskCollaboratorsRepositoryImpl implements QTaskCollaboratorsRepository{

    private final JPAQueryFactory queryFactory;

    public QTaskCollaboratorsRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    @Override
    public List<Long> findAllByTaskListByUserId(Long userId) {
        List<Long> result =
                queryFactory.select(taskCollaborators.taskId).from(taskCollaborators).where(taskCollaborators.userId.eq(userId)).fetch();
        return result;
    }
}
