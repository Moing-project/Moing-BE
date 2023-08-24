package com.moing.moingbe.domain.auth.repository;

import com.moing.moingbe.domain.auth.dao.AuthNickAndImageDao;

public interface QAuthRepository {

    boolean findByEmailExist(String email);
    boolean findByNicknameExist(String nickname);

    String findByPasswordInUsername(String email);

    AuthNickAndImageDao findByProfileImageAndNicknameById(Long id);
}
