package com.moing.moingbe.domain.workspace.repository;

import com.moing.moingbe.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Workspace, Long>, QWorkRepository{

}
