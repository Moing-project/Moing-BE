package com.moing.moingbe.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Schema(description = "회원가입")
public class AuthSignupRequestDto {

    @NotEmpty(message = "이메일이 존재하지 않습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식 오류")
    @Schema(description = "이메일", example = "example@example.com")
    private final String email;

    @NotEmpty(message = "닉네임이 존재하지 않습니다.")
    @Pattern(regexp = "^[a-zA-Z\\d가-힣]{2,8}$", message = "닉네임 형식 오류")
    @Schema(description = "닉네임", example = "Test")
    private final String nickname;

    @NotEmpty(message = "패스워드 존재하지 않습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$", message = "비밀번호 형식 오류")
    @Schema(description = "패스워드", pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,16}$")
    private String password;

    public AuthSignupRequestDto(
             String email,
             String nickname,
             String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}
