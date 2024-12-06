package com.example.band_club.member.event;

import com.example.band_club.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MemberLeft extends MemberEvent{

    public MemberLeft(Member member) {
        super(UUID.randomUUID().toString() , member.getUsername(), member.getId(), member.getClub().getId(), member.getTerminatedAt());
    }
}
