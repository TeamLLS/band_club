package com.example.band_club.member.event;

import com.example.band_club.member.Member;
import com.example.band_club.member.Role;
import com.example.band_club.member.command.ChangeMemberRole;

import java.time.Instant;
import java.util.UUID;

public class MemberRoleChanged extends MemberEvent{

    private Role role;

    public MemberRoleChanged(String username, Member member) {
        super(UUID.randomUUID().toString(), username, member.getId(), member.getClub().getId(), Instant.now());
        this.role = member.getRole();
    }
}
