package com.example.band_club.member.command;

import com.example.band_club.club.Club;
import com.example.band_club.external.feignClient.UserProfile;
import com.example.band_club.member.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMember {

    @NotNull
    private Long clubId;
    private Club club;          //반드시 별도 세팅
    @NotEmpty
    private String username;
    private Role role;          //반드시 별도 세팅
    private String name;
    private Integer birthYear;
    private String gender;

    public CreateMember(String username, Club club){
        this.clubId=club.getId();
        this.club=club;
        this.username=username;
    }

    public void setProfile(UserProfile profile){
        this.name=profile.getName();
        this.birthYear =profile.getBirthYear();
        this.gender=profile.getGender();
    }
}
