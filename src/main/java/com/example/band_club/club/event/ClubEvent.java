package com.example.band_club.club.event;

import com.example.band_club.core.Event;
import com.example.band_club.external.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Getter
@NoArgsConstructor
public abstract class ClubEvent extends Event {



    public ClubEvent(String triggeredBy, String eventId, Long clubId, Instant time) {
        super(eventId, clubId, triggeredBy, time);
    }

    public String typeName(){
        return this.getClass().getTypeName();
    }

    public String Payload(){
        return JsonUtil.toJson(this);
    }
}
