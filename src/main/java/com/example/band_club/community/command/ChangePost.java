package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePost extends CommunityCommand {

    private Long postId;
    private String title;
    private String content;
    private String image;


    public ChangePost(String username, Long clubId, Long postId, String title, String content, String image) {
        super(username, clubId);
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
