package com.example.band_club.member;

public enum MemberStatus {
    ACTIVE("활동중"),
    INACTIVE("휴면"),
    TERMINATED("탈퇴");

    private final String display;

    MemberStatus(String display) {
        this.display = display;
    }

    public String getDisplay(){
        return this.display;
    }
}
