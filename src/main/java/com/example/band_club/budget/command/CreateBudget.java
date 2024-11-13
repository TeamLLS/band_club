package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBudget extends BudgetCommand {

    public CreateBudget(String username, Long clubId) {
        super(clubId, username);
    }
}
