package com.example.band_club.simulation.command;

import com.example.band_club.club.Club;
import com.example.band_club.member.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateMemberDummy {

    @NotNull
    private Long clubId;
    private Club club;

    @NotNull
    private String username;

    @NotNull
    private Role role;

    private String name;
    private Integer birthYear;
    private String gender;
}
