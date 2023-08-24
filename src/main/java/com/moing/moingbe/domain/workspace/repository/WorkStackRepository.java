package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.entity.WorkStack;
import com.moing.moingbe.domain.workspace.enums.WorkStackEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkStackRepository extends JpaRepository<WorkStack, Long> ,QWorkStackRepository {

    Optional<WorkStack> findByWorkIdAndStack(Long workId, WorkStackEnum stack);
}
