package com.example.band_club.community.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentChangeForm {
    @NotNull
    private Long clubId;
    @NotNull
    private Long commentId;
    @NotEmpty
    private String content;
}
