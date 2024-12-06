package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotAttendActivity extends ActivityCommand{

    private Long memberId;
    private Boolean additional;

    public NotAttendActivity(String username,Long activityId, Long memberId, Boolean additional) {
        super(activityId, null, username);
        this.memberId = memberId;
        this.additional = additional;
    }
}
