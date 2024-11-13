package com.example.band_club.club;

import com.example.band_club.club.command.ChangeClub;
import com.example.band_club.club.command.CreateClub;
import com.example.band_club.club.event.ClubChanged;
import com.example.band_club.club.event.ClubClosed;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
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

    public ClubChanged changeInfo(String username, ChangeClub command){

        if(command.isImageChanged()){
            this.image=command.getImageKey();
        }
        if(command.isContactInfoChanged()){
            this.contactInfo=command.getContactInfo();
        }
        if(command.isDescriptionChanged()){
            this.description=command.getDescription();
        }
        if(command.isNameChanged()){
            this.name=command.getName();
        }
        if(command.isStatusChanged() && command.getStatus()!=ClubStatus.CLOSED){
            this.status=command.getStatus();
        }

        return new ClubChanged(username, this);
    }


    public ClubClosed close(String username){
        this.status = ClubStatus.CLOSED;
        this.closedAt = Instant.now();
        return new ClubClosed(username, this);
    }
}
