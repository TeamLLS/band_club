package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePost extends CommunityCommand {

    private String title;
    private String content;
    private String image;
    private Long memberId;
    private String memberName;

    public CreatePost(String username, Long clubId, String title, String content, String image, Long memberId, String memberName) {
        super(username, clubId);
        this.title = title;
        this.content = content;
        this.image = image;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
