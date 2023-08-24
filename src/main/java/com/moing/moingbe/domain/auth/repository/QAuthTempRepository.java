package com.moing.moingbe.domain.auth.repository;

public interface QAuthTempRepository {
    Long deleteByEmail(String email);
}
