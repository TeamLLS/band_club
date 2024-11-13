package com.example.band_club.budget;

import com.example.band_club.budget.form.BudgetRecordForm;
import com.example.band_club.budget.form.MemberRegisterForm;
import com.example.band_club.budget.form.PayBookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budget")
    public ResponseEntity<?> updateBudget(@RequestHeader String username, @Validated @RequestBody BudgetRecordForm form){
        budgetService.updateBudget(username, form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/paybook")
    public ResponseEntity<?> createPayBook(@RequestHeader String username, @Validated @RequestBody PayBookForm form){
        budgetService.createPayBook(username, form);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/paybook/{clubId}/{payBookId}/cancel")
    public ResponseEntity<?> cancelPayBook(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long payBookId){
        budgetService.cancelPayBook(username, clubId, payBookId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/paybook/{clubId}/{payBookId}/close")
    public ResponseEntity<?> closePayBook(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long payBookId){
        budgetService.closePayBook(username, clubId, payBookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/paymember/{clubId}/{payBookId}/all")
    public ResponseEntity<?> registerAllMember(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long payBookId){
        budgetService.registerAll(username, clubId, payBookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/paymember/list")
    public ResponseEntity<?> registerSpecificMember(@RequestHeader String username, @Validated @RequestBody MemberRegisterForm form){
        budgetService.registerSpecificMembers(username, form);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/paymember/{payBookId}/{memberId}/pay")
    public ResponseEntity<?> payMember(@RequestHeader String username, @PathVariable Long payBookId, @PathVariable Long memberId){
        budgetService.changePayMemberStatus(username, payBookId, memberId, "PAID");
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/paymember/{payBookId}/{memberId}/unpay")
    public ResponseEntity<?> unPayMember(@RequestHeader String username, @PathVariable Long payBookId, @PathVariable Long memberId){
        budgetService.changePayMemberStatus(username, payBookId, memberId, "UNPAID");
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/paymember/{payBookId}/{memberId}/late-pay")
    public ResponseEntity<?> latePayMember(@RequestHeader String username, @PathVariable Long payBookId, @PathVariable Long memberId){
        budgetService.changePayMemberStatus(username, payBookId, memberId, "LATE_PAID");
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/paymember/{payBookId}/{memberId}/exclude")
    public ResponseEntity<?> excludeMember(@RequestHeader String username, @PathVariable Long payBookId, @PathVariable Long memberId){
        budgetService.changePayMemberStatus(username, payBookId, memberId, "EXCLUDED");
        return ResponseEntity.ok().build();
    }
}
