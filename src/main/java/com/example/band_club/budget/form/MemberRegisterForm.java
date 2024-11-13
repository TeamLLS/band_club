package com.example.band_club.budget.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterForm {

    @NotNull
    private Long clubId;
    @NotNull
    private Long payBookId;
    @NotNull
    private List<Integer> list;

}
