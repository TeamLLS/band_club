package com.example.band_club.club.command;

import com.example.band_club.club.ClubStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ChangeClub {

    @NotEmpty
    private Long clubId;

    private Boolean nameChanged;
    private String name;

    private Boolean descriptionChanged;
    private String description;

    private Boolean imageChanged;
    private String imageKey;
    private MultipartFile image;

    private Boolean contactInfoChanged;
    private String contactInfo;

    private Boolean statusChanged;
    private ClubStatus status;



    public boolean isNameChanged() {
        return (nameChanged!=null)?nameChanged:false;
    }

    public boolean isDescriptionChanged() {
        return (descriptionChanged!=null)?descriptionChanged:false;
    }

    public boolean isImageChanged() {
        return (imageChanged!=null)?imageChanged:false;
    }

    public boolean isContactInfoChanged() {
        return (contactInfoChanged!=null)?contactInfoChanged:false;
    }

    public boolean isStatusChanged() {
        return (statusChanged!=null)?statusChanged:false;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
