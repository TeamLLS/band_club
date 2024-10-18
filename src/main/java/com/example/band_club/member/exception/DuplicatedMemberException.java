package com.example.band_club.member.exception;

import lombok.Getter;

@Getter
public class DuplicatedMemberException extends RuntimeException{

    private Long clubId;
    private String username;

    public DuplicatedMemberException(String message, Long clubId, String username) {
        super(message);
        this.clubId = clubId;
        this.username = username;
    }

    public DuplicatedMemberException(String message) {
        super(message);
    }

    public DuplicatedMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedMemberException(Throwable cause) {
        super(cause);
    }
}
