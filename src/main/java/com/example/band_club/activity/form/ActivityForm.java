package com.example.band_club.activity.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
public class ActivityForm {

    @NotNull
    private Long clubId;

    @NotNull
    private String name;

    private MultipartFile image;

    private String location;

    private String description;

    private Instant startTime;

    private Instant endTime;
}
