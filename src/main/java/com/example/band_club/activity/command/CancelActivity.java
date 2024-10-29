package com.example.band_club.activity.command;

import lombok.Getter;

@Getter
public class CancelActivity extends ActivityCommand{

    private Long clubId;

    public CancelActivity(String username, Long activityId, Long clubId) {
        super(activityId, username);
        this.clubId = clubId;
    }
}
