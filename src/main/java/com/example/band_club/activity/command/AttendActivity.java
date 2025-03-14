package com.example.band_club.activity.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttendActivity extends ActivityCommand{

    private Long memberId;
    private String memberName;
    private Boolean additional;
    private String profileName;

    public AttendActivity(String username, Long activityId, Long clubId, Long memberId, String memberName, Boolean additional, String profileName) {
        super(activityId, clubId, username);
        this.memberId = memberId;
        this.memberName = memberName;
        this.additional = additional;
        this.profileName = profileName;
    }
}
