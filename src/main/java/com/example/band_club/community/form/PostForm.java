package com.example.band_club.community.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PostForm {

    @NotNull
    private Long clubId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private MultipartFile image;
}
