package com.example.band_club.member.exception;


import lombok.Getter;

@Getter
public class NotMemberException extends RuntimeException{

    private Long clubId;
    private String username;

    public NotMemberException(String message, Long clubId, String username) {
        super(message);
        this.clubId=clubId;
        this.username=username;
    }


    public NotMemberException(String message) {
        super(message);
    }

    public NotMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMemberException(Throwable cause) {
        super(cause);
    }
}
