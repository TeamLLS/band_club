package com.example.band_club.club;

import com.example.band_club.club.command.CreateClub;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String image;

    private String description;

    private String contactInfo;

    @Enumerated(EnumType.STRING)
    private ClubStatus status;
    private Integer memberNum;

    private Instant createdAt;

    private Instant closedAt;

    public Club(CreateClub command){
        this.name = command.getName();
        this.image = command.getImageKey();
        this.description = command.getDescription();
        this.contactInfo = command.getContactInfo();
        this.status = ClubStatus.ACTIVE;
        this.memberNum = 0;
        this.createdAt = Instant.now();
    }

    public void memberNumIncreased(){
        memberNum++;
    }

    public void memberNumDecreased(){
        memberNum--;
    }
}
