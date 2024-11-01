package com.example.band_club.club.event;

import com.example.band_club.club.Club;
import com.example.band_club.club.ClubStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ClubClosed extends ClubEvent{

    private ClubStatus status;

    public ClubClosed(String username, Club club) {
        super(username, UUID.randomUUID().toString(), club.getId(), club.getClosedAt());
        status = club.getStatus();
    }
}

