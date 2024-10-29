package com.example.band_club.club.exception;


import lombok.Getter;

@Getter
public class NotActiveClubException extends RuntimeException{

    private Long clubId;
    private String current;

    public NotActiveClubException(String message, Long clubId, String status) {
        super(message);
        this.clubId = clubId;
        this.current = status;
    }

    public NotActiveClubException(String message) {
        super(message);
    }

    public NotActiveClubException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotActiveClubException(Throwable cause) {
        super(cause);
    }
}
