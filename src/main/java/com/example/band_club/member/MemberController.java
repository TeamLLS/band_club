package com.example.band_club.member;

import com.example.band_club.member.form.ClubMemberItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<?> getClubMemberList(@RequestHeader String username,@RequestParam int pageNo){
        List<ClubMemberItemDto> clubMemberList = memberService.findClubMemberList(username, pageNo);

        Map<String, Object> response = new HashMap<>();
        response.put("list", clubMemberList);

        return ResponseEntity.ok().body(response);
    }
}
