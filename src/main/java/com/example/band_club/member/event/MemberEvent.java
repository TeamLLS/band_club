package com.example.band_club.member.event;

import com.example.band_club.external.JsonUtil;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class MemberEvent {
    private final String eventId;
    private final String triggeredBy;
    private final Long memberId;
    private final Long clubId;
    private final Instant time;

    public MemberEvent(String eventId, String triggeredBy, Long memberId, Long clubId, Instant time) {
        this.eventId = eventId;
        this.triggeredBy = triggeredBy;
        this.memberId = memberId;
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
