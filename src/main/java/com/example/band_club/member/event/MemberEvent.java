package com.example.band_club.member.event;

import com.example.band_club.core.Event;
import com.example.band_club.external.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public abstract class MemberEvent extends Event {

    private Long memberId;


    public MemberEvent(String eventId, String triggeredBy, Long memberId, Long clubId, Instant time) {
        super(eventId, clubId, triggeredBy, time);
        this.memberId = memberId;
    }

    public String typeName(){
        return this.getClass().getTypeName();
    }

    public String Payload(){
        return JsonUtil.toJson(this);
    }
}
