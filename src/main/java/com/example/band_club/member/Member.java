package com.example.band_club.member;


import com.example.band_club.club.Club;
import com.example.band_club.member.command.CreateMember;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    private String username;
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
}
