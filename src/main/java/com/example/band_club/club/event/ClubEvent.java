package com.example.band_club.club.event;

import com.example.band_club.external.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Getter
@NoArgsConstructor
public abstract class ClubEvent {


    private String eventId;
    private String triggeredBy;
    private Long clubId;
    private Instant time;

    public ClubEvent(String triggeredBy, String eventId, Long clubId, Instant time) {
        this.triggeredBy = triggeredBy;
        this.eventId = eventId;
        this.clubId = clubId;
        this.time = time;
    }

    public String typeName(){
        return this.getClass().getTypeName();
    }

    public String Payload(){
        return JsonUtil.toJson(this);
    }
}
