package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeletePost extends CommunityCommand {
    private Long postId;

    public DeletePost(String username, Long clubId, Long postId) {
        super(username, clubId);
        this.postId = postId;
    }
}
