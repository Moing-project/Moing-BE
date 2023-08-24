package com.moing.moingbe.domain.workspace.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.moing.moingbe.domain.workspace.entity.QWorkTeam.workTeam;

public class QWorkTeamRepositoryImpl implements QWorkTeamRepository{

    private final JPAQueryFactory queryFactory;

    public QWorkTeamRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    @Override
    public List<Long> findAllSelectUserIdByWorkId(Long workId) {
        return queryFactory.select(workTeam.userId).from(workTeam).where(workTeam.workId.eq(workId)).fetch();
    }
}
