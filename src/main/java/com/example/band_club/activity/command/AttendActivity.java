package com.example.band_club.activity.command;

import lombok.Getter;

@Getter
public class AttendActivity extends ActivityCommand{

    private Long clubId;
    private Long memberId;
    private String memberName;
    private Boolean additional;
    private String profileName;

    public AttendActivity(String username, Long activityId, Long clubId, Long memberId, String memberName, Boolean additional, String profileName) {
        super(activityId, username);
        this.clubId = clubId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.additional = additional;
        this.profileName = profileName;
    }
}
