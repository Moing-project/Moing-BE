package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.entity.WorkTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkTeamRepository extends JpaRepository<WorkTeam, Long>, QWorkTeamRepository{
    Optional<WorkTeam> findByWorkIdAndUserId(Long workId, Long userId);

    List<WorkTeam> findAllByUserIdOrderByModifiedAtDesc(Long userId);
}
