package com.moing.moingbe.global.enums;

import org.springframework.http.HttpStatus;

public enum AccessCode implements AllCode{
    EMAIL_CORRECT_ERROR("A03", HttpStatus.NOT_ACCEPTABLE),
    ACCESS_TOKEN_ERROR("A08",HttpStatus.BAD_REQUEST),
    WORKSPACE_CREATE_ALLOW("A10",HttpStatus.CREATED),
    TASK_CREATED("A40", HttpStatus.CREATED),
    TASK_LOADING("A42", HttpStatus.CREATED),
    IMAGE_CREATED("A90",HttpStatus.CREATED),
    NULL_ERROR(null, null);

    AccessCode(String errorName, HttpStatus httpStatus) {
        this.code = "ACCESS_" + errorName;
        this.status = httpStatus;
    }
    AccessCode(String errorName, int httpStatus) {
        this.code = "ACCESS_" + errorName;
        this.status = HttpStatus.valueOf(httpStatus);
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus status() {
        return this.status;
    }

    private final String code;
    private final HttpStatus status;
}
