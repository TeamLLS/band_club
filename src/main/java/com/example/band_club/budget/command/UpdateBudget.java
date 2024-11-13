package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBudget extends BudgetCommand {

    @NotNull
    private String description;

    @NotNull
    private Integer amount;

    public UpdateBudget(String username, Long clubId, String description, Integer amount) {
        super(clubId, username);
        this.description = description;
        this.amount = amount;
    }
}
