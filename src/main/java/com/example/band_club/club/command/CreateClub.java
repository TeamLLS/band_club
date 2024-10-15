package com.example.band_club.club.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class CreateClub {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private MultipartFile image;
    private String imageKey;
    private String contactInfo;

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
