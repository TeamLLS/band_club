package com.example.band_club.community.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentForm {
    @NotNull
    private Long clubId;
    @NotNull
    private Long postId;
    private Long baseId;
    @NotEmpty
    private String content;
}
