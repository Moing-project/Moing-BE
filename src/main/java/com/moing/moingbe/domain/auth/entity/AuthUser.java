package com.moing.moingbe.domain.auth.entity;

import com.moing.moingbe.domain.auth.dto.AuthSignupRequestDto;
import com.moing.moingbe.global.enums.AuthGlobalUserEnum;
import com.moing.moingbe.global.maps.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AuthUser extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AuthGlobalUserEnum userRole;

    private String nickname;

    private String profileImage;

    public AuthUser() {
    }

    public AuthUser(AuthTempUser authTempUser){
        this.email = authTempUser.getEmail();
        this.password = authTempUser.getPassword();
        this.userRole = authTempUser.getUserRole();
        this.nickname = authTempUser.getNickname();
    }

    public AuthUser(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.userRole = AuthGlobalUserEnum.FREE;
        this.nickname = nickname;
    }

    public AuthUser(AuthSignupRequestDto requestDto) {
        this(requestDto.getEmail(), requestDto.getPassword(), requestDto.getNickname());
    }

}
