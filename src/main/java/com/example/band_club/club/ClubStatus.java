package com.example.band_club.club;

public enum ClubStatus {
    ACTIVE("운영중"),
    CLOSED("운영종료"),
    RECRUITING("모집중");

    private final String display;

    ClubStatus(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
