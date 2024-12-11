package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteComment extends CommunityCommand {
    private Long commentId;
    private Boolean authorized;

    public DeleteComment(String username, Long clubId, Long commentId, Boolean authorized) {
        super(username, clubId);
        this.commentId = commentId;
        this.authorized = authorized;
    }
}
