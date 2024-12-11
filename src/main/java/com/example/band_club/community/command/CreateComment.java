package com.example.band_club.community.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateComment extends CommunityCommand  {

    private Long postId;
    private Long baseId;
    private String content;
    private Long memberId;
    private String memberName;

    public CreateComment(String username, Long clubId, Long postId, Long baseId, String content, Long memberId, String memberName) {
        super(username, clubId);
        this.postId = postId;
        this.baseId = baseId;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
