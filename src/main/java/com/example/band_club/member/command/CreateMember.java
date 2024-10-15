package com.example.band_club.member.command;

import com.example.band_club.club.Club;
import com.example.band_club.external.feignClient.UserProfile;
import com.example.band_club.member.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMember {

    @NotEmpty
    private Long clubId;
    private Club club;          //반드시 별도 세팅
    @NotEmpty
    private String username;
    private Role role;          //반드시 별도 세팅
    private String name;
    private Integer birthYear;
    private String gender;

    public CreateMember(Club club, String username){
        this.clubId=club.getId();
        this.club=club;
        this.username=username;
    }

    public void setProfile(UserProfile profile){
        this.name=profile.getName();
        this.birthYear =profile.getBirthYear();
        this.gender=profile.getGender();
    }
    public void setCLub(Club club){
        this.club=club;
    }
    public void setRole(Role role){
        this.role=role;
    }
}
