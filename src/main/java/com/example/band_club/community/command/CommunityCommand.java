package com.example.band_club.community.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public abstract class CommunityCommand {

    @NotNull
    private String username;

    private Long clubId;

    public CommunityCommand(String username, Long clubId) {
        this.username = username;
        this.clubId = clubId;
    }
}
