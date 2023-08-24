package com.moing.moingbe.domain.auth.repository;

import com.moing.moingbe.domain.auth.entity.AuthTempUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTempRepository extends JpaRepository<AuthTempUser, Long>, QAuthTempRepository{
    Optional<AuthTempUser> findByEmail(String email);
}
