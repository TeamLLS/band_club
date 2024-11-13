package com.example.band_club.budget.command;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterPayMember extends BudgetCommand {

    @NotNull
    private Long payBookId;
    @NotNull
    private Long memberId;
    private String memberName;
    @NotNull
    private String profileName;


    public RegisterPayMember(String username, Long payBookId, Long clubId, Long memberId, String memberName, String profileName) {
        super(clubId, username);
        this.payBookId = payBookId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.profileName = profileName;
    }
}
