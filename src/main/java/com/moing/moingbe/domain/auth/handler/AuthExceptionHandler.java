package com.moing.moingbe.domain.auth.handler;

import com.moing.moingbe.domain.auth.exception.AuthDuplicationException;
import com.moing.moingbe.global.dto.MessageResponseDto;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.slf4j.LoggerFactory.getLogger;

public interface AuthExceptionHandler {

    @ExceptionHandler(AuthDuplicationException.class)
    default ResponseEntity<MessageResponseDto> postExceptionHandler(AuthDuplicationException e) {
        MessageResponseDto response = MessageResponseDto.out(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }

    private static Logger logger() {
        final class LogHolder {
            private static final Logger LOGGER = getLogger(AuthExceptionHandler.class);
        }
        return LogHolder.LOGGER;
    }
}
