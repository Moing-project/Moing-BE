package com.moing.moingbe.domain.auth.exception;

import com.moing.moingbe.global.exception.GlobalStateException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthDuplicationException extends GlobalStateException {
    public AuthDuplicationException(HttpStatus status, String message) {
        super(message, status);
    }
    public AuthDuplicationException(int status, String message) {
        super(message, HttpStatus.valueOf(status));
    }
}
