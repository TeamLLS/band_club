package com.example.band_club.member.form;

import com.example.band_club.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberClubItemDto {


    private Long clubId;
    private Long memberId;
    private String image;
    private String clubName;
    private String clubStatus;
    private String role;
    private String memberStatus;




    public MemberClubItemDto(Member member, String imageResource) {
        this.clubId = member.getClub().getId();
        this.memberId = member.getId();
        this.image = imageResource;
        this.clubName = member.getClub().getName();
        this.clubStatus = member.getClub().getStatus().getDisplay();
        this.role = member.getRole().getDisplay();
        this.memberStatus = member.getStatus().getDisplay();

    }
}

