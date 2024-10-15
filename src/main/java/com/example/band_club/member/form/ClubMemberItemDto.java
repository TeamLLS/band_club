package com.example.band_club.member.form;

import com.example.band_club.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubMemberItemDto {

    private Long clubId;
    private Long memberId;
    private String image;
    private String name;
    private String role;

    public ClubMemberItemDto(Member member, String imageResource) {
        this.clubId = member.getClub().getId();
        this.memberId = member.getId();
        this.image = imageResource;
        this.name = member.getClub().getName();
        this.role = member.getRole().getDisplay();
    }
}

