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
public class PostChangeForm {

    @NotNull
    private Long clubId;
    @NotNull
    private Long postId;

    @NotEmpty
    private String title;
    private Boolean titleChanged;

    @NotEmpty
    private String content;
    private Boolean contentChanged;

    private MultipartFile image;
    private Boolean imageChanged;

    public boolean isTitleChanged(){
        return (titleChanged!=null)?titleChanged:false;
    }

    public boolean isContentChanged(){
        return (contentChanged!=null)?contentChanged:false;
    }

    public boolean isImageChanged(){
        return (imageChanged!=null)?imageChanged:false;
    }
}
