package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CloseBudget extends BudgetCommand {

    public CloseBudget(String username, Long clubId) {
        super(clubId, username);
    }
}
