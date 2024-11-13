package com.example.band_club.budget.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePayMemberStatus extends BudgetCommand {

    @NotNull
    private Long payBookId;
    @NotNull
    private Long memberId;
    @NotNull
    private String status;


    public ChangePayMemberStatus(String username, Long payBookId, Long memberId, String status) {
        super(null, username);
        this.payBookId = payBookId;
        this.memberId = memberId;
        this.status = status;
    }

}
