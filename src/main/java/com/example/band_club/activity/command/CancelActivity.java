package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CancelActivity extends ActivityCommand{

    public CancelActivity(String username, Long activityId, Long clubId) {
        super(activityId, clubId, username);
    }
}
