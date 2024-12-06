package com.example.band_club.member;


import com.example.band_club.club.Club;
import com.example.band_club.member.command.ChangeMemberRole;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.event.MemberBanned;
import com.example.band_club.member.event.MemberRoleChanged;
import com.example.band_club.member.event.MemberLeft;
import com.example.band_club.simulation.command.CreateMemberDummy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @NotNull
    private String username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private Integer birthYear;
    private String gender;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Instant createdAt;

    private Instant terminatedAt;

    public Member(CreateMember command){
        this.club = command.getClub();
        this.username = command.getUsername();
        this.role = command.getRole();
        this.name = command.getName();
        this.gender = command.getGender();
        this.birthYear = command.getBirthYear();
        this.status = MemberStatus.ACTIVE;
        this.createdAt = Instant.now();
    }

    public Member(CreateMemberDummy command){
        this.club = command.getClub();
        this.username = command.getUsername();
        this.role = command.getRole();
        this.name = command.getName();
        this.gender = command.getGender();
        this.birthYear = command.getBirthYear();
        this.status = MemberStatus.ACTIVE;
        this.createdAt = Instant.now();
    }


    public MemberRoleChanged changeRole(String username, ChangeMemberRole command){
        this.role = command.getRole();

        return new MemberRoleChanged(username, this);
    }

    public MemberLeft left(){
        this.status = MemberStatus.TERMINATED;
        this.terminatedAt = Instant.now();

        return new MemberLeft(this);
    }

    public MemberBanned ban(String username){
        this.status = MemberStatus.TERMINATED;
        this.terminatedAt = Instant.now();

        return new MemberBanned(username,this);
    }
}
