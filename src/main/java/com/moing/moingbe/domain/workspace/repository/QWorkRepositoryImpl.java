package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.dao.WorkMainDao;
import com.moing.moingbe.domain.workspace.entity.WorkTeam;
import com.moing.moingbe.domain.workspace.entity.Workspace;
import com.moing.moingbe.domain.workspace.enums.WorkAllowEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moing.moingbe.domain.workspace.entity.QWorkspace.workspace;

@Repository
public class QWorkRepositoryImpl implements QWorkRepository {

    private final JPAQueryFactory queryFactory;

    public QWorkRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Workspace> findAllOrderByAllowType() {
        return queryFactory.select(workspace).from(workspace).where(workspace.allowType.ne(WorkAllowEnum.SECRET)).fetch();
    }

    @Override
    public List<Workspace> findAllOrderByAllowTypeToSort() {
        return queryFactory.select(workspace).from(workspace).where(workspace.allowType.ne(WorkAllowEnum.SECRET)).orderBy(workspace.createdAt.desc()).fetch();
    }

    @Override
    public List<WorkMainDao> findAllByListWorkIdToMainDao(List<WorkTeam> workList) {
        return queryFactory.select(workspace.id, workspace.imageSrc, workspace.title)
                .from(workspace).where(workspace.id.in(workList.stream().map(WorkTeam::getWorkId).toList()))
                .fetch().stream()
                .map(e -> new WorkMainDao(
                    e.get(workspace.id),
                    e.get(workspace.imageSrc),
                    e.get(workspace.title))).toList();
    }
}
