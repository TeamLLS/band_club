package com.example.band_club.club.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class ClubEventJpo {

    @Id
    private String eventId;
    private Long clubId;
    private String eventType;
    @Lob
    private String payload;
    private Instant time;

    public ClubEventJpo(ClubEvent clubEvent){
        this.eventId= clubEvent.getEventId();
        this.clubId=clubEvent.getClubId();
        this.eventType= clubEvent.typeName();
        this.payload= clubEvent.Payload();
        this.time= clubEvent.getTime();
    }
}
