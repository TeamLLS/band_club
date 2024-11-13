package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CancelPayBook extends BudgetCommand {

    @NotNull
    private Long payBookId;

    public CancelPayBook(String username, Long payBookId, Long clubId) {
        super(clubId, username);
        this.payBookId = payBookId;
    }
}
