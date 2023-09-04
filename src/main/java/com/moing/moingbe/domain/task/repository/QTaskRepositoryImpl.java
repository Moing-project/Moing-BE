package com.moing.moingbe.domain.task.repository;

import com.moing.moingbe.domain.task.dao.TaskKanbanDao;
import com.moing.moingbe.domain.task.entity.Task;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.moing.moingbe.domain.task.entity.QTask.task;

@Repository
public class QTaskRepositoryImpl implements QTaskRepository {

    private final JPAQueryFactory queryFactory;

    public QTaskRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<TaskKanbanDao> getListTaskKanban(List<Long> taskList) {
        List<TaskKanbanDao> result = queryFactory.select(task.id, task.title, task.description, task.statusEnum)
                .from(task)
                .where(task.id.in(taskList))
                .orderBy(task.id.asc())
                .fetch().stream()
                .map(e ->
                        new TaskKanbanDao(
                                e.get(task.id),
                                e.get(task.title),
                                e.get(task.description),
                                e.get(task.statusEnum)))
                .toList();
        return result;
    }

    @Override
    public Long finalTask(Long workId) {
        return queryFactory.select(task.taskId).from(task).where(task.workId.eq(workId)).orderBy(task.id.desc()).fetchOne();
    }
    @Override
    public Page<Task> taskList(Long workId, Pageable pageable) {
        List<Task> taskList = queryFactory.selectFrom(task)
                .where(task.workId.eq(workId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = taskList.size();
        return new PageImpl<>(taskList, pageable, total);
    }
}
