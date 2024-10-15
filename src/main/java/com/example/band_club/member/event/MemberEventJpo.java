package com.example.band_club.member.event;

import com.example.band_club.club.event.ClubEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
public class MemberEventJpo {

    @Id
    private String eventId;
    private Long clubId;
    private Long memberId;
    private String eventType;
    @Lob
    private String payload;
    private Instant time;

    public MemberEventJpo(MemberEvent memberEvent){
        this.eventId = memberEvent.getEventId();
        this.memberId = memberEvent.getMemberId();
        this.clubId = memberEvent.getClubId();
        this.eventType = memberEvent.typeName();
        this.payload = memberEvent.Payload();
        this.time = memberEvent.getTime();
    }
}
