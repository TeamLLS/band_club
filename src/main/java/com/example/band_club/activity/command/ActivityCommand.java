package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class ActivityCommand {

    private Long activityId;
    private Long clubId;
    private String username;

    public ActivityCommand(Long activityId, Long clubId, String username) {
        this.activityId = activityId;
        this.clubId = clubId;
        this.username = username;
    }
}
