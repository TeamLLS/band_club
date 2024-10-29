package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class ActivityCommand {

    private Long activityId;

    private String username;

    public ActivityCommand(Long activityId, String username) {
        this.activityId = activityId;
        this.username = username;
    }
}
