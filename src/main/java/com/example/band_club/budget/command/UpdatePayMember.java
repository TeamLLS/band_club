package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class UpdatePayMember extends BudgetCommand {

    @NotNull
    private Long payBookId;
    @NotNull
    private Long memberId;
    @NotNull
    private String status;
    private Instant time;

    public UpdatePayMember(String username, Long payBookId, Long memberId, String status, Instant time) {
        super(null, username);
        this.payBookId = payBookId;
        this.memberId = memberId;
        this.status = status;
        this.time = time;
    }

}
