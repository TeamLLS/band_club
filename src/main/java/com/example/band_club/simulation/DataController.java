package com.example.band_club.simulation;


import com.example.band_club.simulation.command.CreateMemberDummy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/club/dummy")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;


    @PostMapping("/member")
    public ResponseEntity<?> registerMemberDummy(@RequestHeader String username,@Validated @RequestBody CreateMemberDummy command){
        Long memberId = dataService.createMemberDummy(username, command);

        Map<String, Object> result = new HashMap<>();
        result.put("id", memberId);

        return ResponseEntity.ok().body(result);
    }

}
