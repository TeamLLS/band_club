package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePayBook extends BudgetCommand {


    private Integer amount;

    private String name;

    private String description;

    public CreatePayBook(String username, Long clubId, Integer amount, String name, String description) {
        super(clubId, username);
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
}
