package com.example.band_club.member.event;


import com.example.band_club.member.Member;
import com.example.band_club.member.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MemberCreated extends MemberEvent {
    private String username;
    private Role role;
    private String name;
    private Integer birthYear;
    private String gender;

    public MemberCreated(String username, Member member) {
        super(UUID.randomUUID().toString(),  username, member.getId(), member.getClub().getId(), member.getCreatedAt());
        this.username = member.getUsername();
        this.role = member.getRole();
        this.name = member.getName();
        this.birthYear = member.getBirthYear();
        this.gender = member.getGender();
    }
}
