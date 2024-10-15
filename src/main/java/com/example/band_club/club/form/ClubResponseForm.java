package com.example.band_club.club.form;


import com.example.band_club.club.Club;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubResponseForm {

    private Long clubId;
    private String name;
    private String description;
    private String image;
    private String contactInfo;
    private String status;
    private Integer memberNum;

    public ClubResponseForm(Club club, String imageResource) {
        this.clubId = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.image = imageResource;
        this.contactInfo = club.getContactInfo();
        this.status = club.getStatus().getDisplay();
        this.memberNum = club.getMemberNum();
    }
}
