package com.example.band_club.member.exception;

import lombok.Getter;

@Getter
public class InsufficientMemberException extends RuntimeException{

    private String request;

    private String required;

    public InsufficientMemberException(String message, String request, String required) {
        super(message);
        this.request=request;
        this.required = required;
    }

    public InsufficientMemberException(String message) {
        super(message);
    }

    public InsufficientMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientMemberException(Throwable cause) {
        super(cause);
    }
}
