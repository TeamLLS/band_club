package com.example.band_club.activity;


import com.example.band_club.activity.form.ActivityForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestHeader String username, @Validated @ModelAttribute ActivityForm form){
        activityService.create(username, form);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clubId}/{activityId}/close")
    public ResponseEntity<?> closeActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId){

        activityService.close(username, clubId, activityId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{clubId}/{activityId}/cancel")
    public ResponseEntity<?> cancelActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId){

        activityService.cancel(username, clubId, activityId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clubId}/{activityId}/attend")
    public ResponseEntity<?> attendActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId){

        activityService.attend(username, activityId, clubId);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/{clubId}/{activityId}/attend/additional")
    public ResponseEntity<?> additionalAttendActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId, @RequestParam(required = false) String target){

        activityService.additionalAttend(username, activityId, clubId, target);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clubId}/{activityId}/not-attend")
    public ResponseEntity<?> notAttendActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId){

        activityService.notAttend(username, activityId, clubId);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/{clubId}/{activityId}/not-attend/additional")
    public ResponseEntity<?> additionalNotAttendActivity(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long activityId, @RequestParam(required = false) String target){

        activityService.additionalNotAttend(username, activityId, clubId, target);

        return ResponseEntity.ok().build();
    }
}
