package com.example.band_club.member;

import com.example.band_club.member.command.ChangeMemberRole;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.form.MemberClubItemDto;
import com.example.band_club.member.form.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/club/list")
    public ResponseEntity<?> getMemberClubList(@RequestHeader String username,@RequestParam int pageNo){
        List<MemberClubItemDto> memberClubList = memberService.getMemberClubList(username, pageNo, 50);

        Map<String, Object> response = new HashMap<>();
        response.put("list", memberClubList);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> registerMember(@RequestHeader String username, @Validated @RequestBody CreateMember command){
        Long memberId = memberService.registerRegular(username, command);

        Map<String, Object> response = new HashMap<>();
        response.put("memberId", memberId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{clubId}/list")
    public ResponseEntity<?> getMemberList(@PathVariable Long clubId, @RequestParam int pageNo){
        List<MemberDto> memberList = memberService.getMemberList(clubId, pageNo, 50);

        Map<String, Object> response = new HashMap<>();
        response.put("list", memberList);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/role")
    public ResponseEntity<?> changeMemberRole(@RequestHeader String username, @Validated @RequestBody ChangeMemberRole command){

        memberService.changeMemberRole(username, command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{clubId}/withdraw")
    public ResponseEntity<?> withdrawMember(@RequestHeader String username,@PathVariable Long clubId){

        memberService.withdrawMember(username, clubId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{memberId}/ban")
    public ResponseEntity<?> banMember(@RequestHeader String username, @PathVariable Long memberId){

        memberService.banMember(username, memberId);

        return ResponseEntity.ok().build();
    }
}
