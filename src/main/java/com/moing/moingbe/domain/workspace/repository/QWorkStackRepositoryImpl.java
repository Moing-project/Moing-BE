package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moing.moingbe.domain.workspace.entity.QWorkStack.workStack;

@Repository
public class QWorkStackRepositoryImpl implements QWorkStackRepository{

    private final JPAQueryFactory queryFactory;

    public QWorkStackRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<WorkStackEnum> findAllByWorkIdToStack(Long workId){
        return queryFactory.select(workStack.stack).from(workStack).where(workStack.workId.eq(workId)).fetch();
    }
}
