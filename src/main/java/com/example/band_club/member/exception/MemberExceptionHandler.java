package com.example.band_club.member.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(NotMemberException.class)
    public ResponseEntity<?> handleCustomException(NotMemberException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "NOT_MEMBER_EXCEPTION");
        response.put("message", ex.getMessage());
        response.put("clubId", ex.getClubId());
        response.put("username", ex.getUsername());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicatedMemberException.class)
    public ResponseEntity<?> handleCustomException(DuplicatedMemberException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "DUPLICATED_MEMBER_EXCEPTION");
        response.put("message", ex.getMessage());
        response.put("clubId", ex.getClubId());
        response.put("username", ex.getUsername());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(NotActiveMemberException.class)
    public ResponseEntity<?> handleCustomException(NotActiveMemberException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "NOT_ACTIVE_MEMBER_EXCEPTION");
        response.put("message", ex.getMessage());
        response.put("memberId", ex.getMemberId());
        response.put("status", ex.getCurrent());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }


    @ExceptionHandler(InsufficientMemberException.class)
    public ResponseEntity<?> handleCustomException(InsufficientMemberException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "INSUFFICIENT_MEMBER_EXCEPTION");
        response.put("message", ex.getMessage());
        response.put("request", ex.getRequest());
        response.put("required", ex.getRequired());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
