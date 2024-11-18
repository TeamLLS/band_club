package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloseActivity extends ActivityCommand{

    public CloseActivity(String username, Long activityId, Long clubId) {
        super(activityId, clubId, username);
    }
}
