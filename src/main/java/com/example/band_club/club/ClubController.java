package com.example.band_club.club;

import com.example.band_club.club.command.CreateClub;
import com.example.band_club.club.form.ClubResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<?> createClub(@RequestHeader String username, @Validated @ModelAttribute CreateClub command){

        Long clubId = clubService.createClub(username, command);

        Map<String, Object> response = new HashMap<>();
        response.put("clubId", clubId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<?> getClubInfo(@PathVariable Long clubId){
        ClubResponseForm clubInfo = clubService.getClubInfo(clubId);

        Map<String, Object> response = new HashMap<>();
        response.put("clubInfo", clubInfo);

        return ResponseEntity.ok().body(response);
    }


    @PatchMapping("/{clubId}")
    public ResponseEntity<?> changeUserInfo(@PathVariable Long clubId){
        return ResponseEntity.ok().build();
    }
}
