package com.example.band_club.activity.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;

@Getter
public class OpenActivity extends ActivityCommand{

    @NotNull
    private Long clubId;

    @NotNull
    private String name;

    private String image;

    private String description;

    private Instant startTime;

    private Instant endTime;

    public OpenActivity(String username, Long clubId, String name, String image, String description, Instant startTime, Instant endTime) {
        super(null, username);
        this.clubId = clubId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
