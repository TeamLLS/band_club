package com.example.band_club.club.exception;

import com.example.band_club.member.exception.DuplicatedMemberException;
import com.example.band_club.member.exception.InsufficientMemberException;
import com.example.band_club.member.exception.NotActiveMemberException;
import com.example.band_club.member.exception.NotMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.NotActiveException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ClubExceptionHandler {

    @ExceptionHandler(NotActiveClubException.class)
    public ResponseEntity<?> handleCustomException(NotActiveClubException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "NOT_ACTIVE_CLUB_EXCEPTION");
        response.put("message", ex.getMessage());
        response.put("clubId", ex.getClubId());
        response.put("status", ex.getCurrent());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}

