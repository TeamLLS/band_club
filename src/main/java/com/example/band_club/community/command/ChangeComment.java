package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeComment extends CommunityCommand  {
    private Long commentId;
    private String content;
    private Boolean authorized;

    public ChangeComment(String username, Long clubId, Long commentId, String content, Boolean authorized) {
        super(username, clubId);
        this.commentId = commentId;
        this.content = content;
        this.authorized = authorized;
    }
}
