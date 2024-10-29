package com.example.band_club.member.event;

import com.example.band_club.external.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public abstract class MemberEvent {
    private String eventId;
    private String triggeredBy;
    private Long memberId;
    private Long clubId;
    private Instant time;

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
