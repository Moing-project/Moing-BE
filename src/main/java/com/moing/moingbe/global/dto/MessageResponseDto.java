package com.moing.moingbe.global.dto;

import com.moing.moingbe.global.enums.AccessCode;
import com.moing.moingbe.global.enums.AllCode;
import com.moing.moingbe.global.enums.DeniedCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    Integer status;
    String msg;
    public MessageResponseDto() {
    }
    public MessageResponseDto(AllCode allCode) {
        this.msg = allCode.code();
        this.status = allCode.status().value();
    }
    protected MessageResponseDto(Integer status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    protected MessageResponseDto(HttpStatus status, String msg) {
        this.msg = msg;
        this.status = status.value();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static MessageResponseDto out(Integer status, String msg){
        return new MessageResponseDto(status, msg);
    }
    public static MessageResponseDto out(HttpStatus status, String msg){
        return new MessageResponseDto(status, msg);
    }

    public static MessageResponseDto out(AllCode code){
        return new MessageResponseDto(code);
    }
}
