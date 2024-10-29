package com.example.band_club.member.event;

import com.example.band_club.member.Member;
import com.example.band_club.member.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MemberWithdrawn extends MemberEvent{
    private MemberStatus status;

    public MemberWithdrawn(Member member) {
        super(UUID.randomUUID().toString() , member.getUsername(), member.getId(), member.getClub().getId(), member.getTerminatedAt());

        this.status = member.getStatus();
    }
}
