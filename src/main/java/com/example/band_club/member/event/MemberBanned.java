package com.example.band_club.member.event;

import com.example.band_club.member.Member;
import com.example.band_club.member.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class MemberBanned extends MemberEvent{
    public MemberBanned(String username, Member member) {
        super(UUID.randomUUID().toString(), username, member.getId(), member.getClub().getId(), member.getTerminatedAt());
    }
}
