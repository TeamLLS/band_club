package com.example.band_club.club.event;

import com.example.band_club.external.JsonUtil;
import lombok.Getter;

import java.time.Instant;


@Getter
public abstract class ClubEvent {


    private final String eventId;
    private final String triggeredBy;
    private final Long clubId;
    private final Instant time;

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
