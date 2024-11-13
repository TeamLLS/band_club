package com.example.band_club.member.form;


import com.example.band_club.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class MemberDto {

    private Long memberId;
    private Long clubId;
    private String username;

    private String roleName;
    private Integer roleRank;

    private String name;
    private Integer age;
    private String gender;
    private String status;


    public MemberDto(Member member){
        this.memberId = member.getId();
        this.clubId = member.getClub().getId();
        this.username = member.getUsername();
        this.roleName = member.getRole().getDisplay();
        this.roleRank = member.getRole().getRank();
        this.name = member.getName();
        this.age = Year.now().getValue() - member.getBirthYear();
        this.gender = member.getGender();
        this.status = member.getStatus().getDisplay();
    }
}
