package com.example.band_club.club.event;


import com.example.band_club.club.Club;
import lombok.Getter;

import java.util.UUID;


@Getter
public class ClubCreated extends ClubEvent {
    private String name;

    private String image;

    private String description;

    private String contactInfo;


    public ClubCreated(String username, Club club) {
        super(username, UUID.randomUUID().toString(), club.getId(), club.getCreatedAt());
        this.name = club.getName();
        this.image = club.getImage();
        this.description = club.getDescription();
        this.contactInfo = club.getContactInfo();
    }
}
