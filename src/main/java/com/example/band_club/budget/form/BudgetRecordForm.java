package com.example.band_club.budget.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BudgetRecordForm {

    @NotNull
    private Long clubId;

    @NotNull
    private String description;

    @NotNull
    private Integer amount;
}
