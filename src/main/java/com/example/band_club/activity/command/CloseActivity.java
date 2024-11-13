package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloseActivity extends ActivityCommand{

    private Long clubId;

    public CloseActivity(String username, Long activityId, Long clubId) {
        super(activityId, username);
        this.clubId = clubId;
    }
}
