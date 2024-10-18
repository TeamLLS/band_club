package com.example.band_club.member.exception;


import lombok.Getter;

@Getter
public class NotActiveMemberException extends RuntimeException{

    private Long memberId;
    private String current;

    public NotActiveMemberException(String message, Long memberId, String status) {
        super(message);
        this.current = status;
    }

    public NotActiveMemberException(String message) {
        super(message);
    }

    public NotActiveMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotActiveMemberException(Throwable cause) {
        super(cause);
    }
}
