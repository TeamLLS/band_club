package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class CreatePayBook extends BudgetCommand {


    private Integer amount;
    private String name;
    private String memberName;
    private String description;
    private Instant deadline;

    public CreatePayBook(String username, Long clubId, String memberName, Integer amount, String name, String description, Instant deadline) {
        super(clubId, username);
        this.name = name;
        this.memberName = memberName;
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
    }
}
