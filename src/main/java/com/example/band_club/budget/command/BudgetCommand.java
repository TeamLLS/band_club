package com.example.band_club.budget.command;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class BudgetCommand {

    private Long clubId;
    private String username;

    public BudgetCommand(Long clubId, String username) {
        this.clubId = clubId;
        this.username = username;
    }
}
