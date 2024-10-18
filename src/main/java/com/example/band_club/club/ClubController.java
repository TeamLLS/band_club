package com.example.band_club.club;

import com.example.band_club.club.command.ChangeClub;
import com.example.band_club.club.command.CreateClub;
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
        return ResponseEntity.ok().body(clubService.getClubInfo(clubId));
    }


    @PatchMapping
    public ResponseEntity<?> changeClubInfo(@RequestHeader String username, @ModelAttribute ChangeClub command){

        clubService.changeClubInfo(username, command);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{clubId}/close")
    public ResponseEntity<?> closeClub(@RequestHeader String username, @PathVariable Long clubId){

        clubService.closeClub(username, clubId);

        return ResponseEntity.ok().build();
    }
}
