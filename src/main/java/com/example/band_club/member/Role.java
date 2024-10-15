package com.example.band_club.member;

public enum Role {
    OWNER(3, "회장"),
    MANAGER(2, "관리자"),
    REGULAR(1, "일반");

    private final int rank;
    private final String display;

    Role(int rank, String display) {
        this.rank = rank;
        this.display = display;
    }

    public int getRank(){
        return this.rank;
    }

    public String getDisplay() {
        return display;
    }
}
