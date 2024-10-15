package com.example.band_club.club.command;

import com.example.band_club.club.ClubStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.web.multipart.MultipartFile;

@Getter
//@Setter
@AllArgsConstructor
public class ChangeClub {

    @NotEmpty
    private Long clubId;

    private String name;
    private Boolean nameChanged;

    private String description;
    private Boolean descriptionChanged;

    private MultipartFile image;
    private Boolean imageChanged;
    private String imageKey;

    private String contactInfo;
    private Boolean contactInfoChanged;

    private ClubStatus status;
    private Boolean statusChanged;


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
