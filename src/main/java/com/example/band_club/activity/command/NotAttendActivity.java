package com.example.band_club.activity.command;

import lombok.Getter;

@Getter
public class NotAttendActivity extends ActivityCommand{

    private Long activityId;
    private Long memberId;
    private Boolean additional;

    public NotAttendActivity(String username,Long activityId, Long memberId, Boolean additional) {
        super(activityId, username);
        this.activityId = activityId;
        this.memberId = memberId;
        this.additional = additional;
    }
}
