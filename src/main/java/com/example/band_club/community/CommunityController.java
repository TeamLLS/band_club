package com.example.band_club.community;

import com.example.band_club.community.form.CommentChangeForm;
import com.example.band_club.community.form.CommentForm;
import com.example.band_club.community.form.PostChangeForm;
import com.example.band_club.community.form.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/board/post")
    public ResponseEntity<?> createPost(@RequestHeader String username, @ModelAttribute PostForm form){
        communityService.createPost(username, form);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/board/post")
    public ResponseEntity<?> changePost(@RequestHeader String username, @ModelAttribute PostChangeForm form){
        communityService.changePost(username, form);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/board/{clubId}/post/{postId}")
    public ResponseEntity<?> deletePost(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long postId){
        communityService.deletePost(username, clubId, postId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/board/comment")
    private ResponseEntity<?> createComment(@RequestHeader String username, @RequestBody CommentForm form){
        communityService.createComment(username, form);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/board/comment")
    public ResponseEntity<?> changeComment(@RequestHeader String username, @RequestBody CommentChangeForm form){
        communityService.changeComment(username, form);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/board/{clubId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestHeader String username, @PathVariable Long clubId, @PathVariable Long commentId){
        communityService.deleteComment(username, clubId, commentId);

        return ResponseEntity.ok().build();
    }
}
