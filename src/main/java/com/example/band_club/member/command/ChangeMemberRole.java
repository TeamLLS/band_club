package com.example.band_club.member.command;

import com.example.band_club.member.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeMemberRole {

    @NotNull
    private Long memberId;

    @NotNull
    private Role role;
}
