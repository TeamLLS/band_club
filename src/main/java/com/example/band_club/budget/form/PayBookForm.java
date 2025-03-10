package com.example.band_club.budget.form;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class PayBookForm {

    @NotNull
    private Long clubId;
    @NotNull
    private Integer amount;
    private String name;

    private String description;
    private Instant deadline;

}
