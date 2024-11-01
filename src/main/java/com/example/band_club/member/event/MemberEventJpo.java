package com.example.band_club.member.event;

import com.example.band_club.club.event.ClubEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class MemberEventJpo {

    @Id
    private String eventId;
    private Long clubId;
    private Long memberId;

    private String triggeredBy;
    private String eventType;
    @Lob
    private String payload;
    private Instant time;

    public MemberEventJpo(MemberEvent memberEvent){
        this.eventId = memberEvent.getEventId();
        this.memberId = memberEvent.getMemberId();
        this.clubId = memberEvent.getClubId();
        this.triggeredBy = memberEvent.getTriggeredBy();
        this.eventType = memberEvent.typeName();
        this.payload = memberEvent.Payload();
        this.time = memberEvent.getTime();
    }
}
