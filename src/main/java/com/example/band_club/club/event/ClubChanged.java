package com.example.band_club.club.event;

import com.example.band_club.club.Club;
import com.example.band_club.club.ClubStatus;
import com.example.band_club.club.command.ChangeClub;
import com.example.band_club.club.command.CreateClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ClubChanged extends ClubEvent{

    private String name;
    private String description;
    private String image;
    private String contactInfo;
    private ClubStatus status;


    public ClubChanged(String username, Club club) {
        super(username, UUID.randomUUID().toString(), club.getId(), Instant.now());
        this.name = club.getName();
        this.description = club.getDescription();
        this.image = club.getImage();
        this.contactInfo = club.getContactInfo();
        this.status = club.getStatus();
    }
}
