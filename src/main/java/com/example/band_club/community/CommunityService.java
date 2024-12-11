package com.example.band_club.community;

import com.example.band_club.club.ClubService;
import com.example.band_club.club.form.ClubDto;
import com.example.band_club.club.policy.ClubStatusAccessPolicy;
import com.example.band_club.community.command.*;
import com.example.band_club.community.form.CommentChangeForm;
import com.example.band_club.community.form.CommentForm;
import com.example.band_club.community.form.PostChangeForm;
import com.example.band_club.community.form.PostForm;
import com.example.band_club.external.kafka.KafkaProducerService;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.Role;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.member.policy.MemberRoleAccessPolicy;
import com.example.band_club.member.policy.MemberStatusAccessPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {
    private final ClubService clubService;
    private final MemberService memberService;
    private final S3Service s3Service;
    private final KafkaProducerService kafkaProducerService;

    public void createPost(String username, PostForm form){
        ClubDto club = clubService.getClubInfo(form.getClubId());
        MemberDto requester = memberService.getMemberInfo(form.getClubId(), username);

        ClubStatusAccessPolicy.isActive(club);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        String imageKey;
        if(form.getImage()==null || form.getImage().isEmpty()){
            imageKey = "";
        }else{
            imageKey = s3Service.saveImage("club/" + club.getName()
                    + "/board/image", form.getTitle(), form.getImage());
        }

        CreatePost command = new CreatePost(username, club.getClubId(), form.getTitle(), form.getContent(),
                imageKey, requester.getMemberId(), requester.getName());

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }

    public void changePost(String username, PostChangeForm form){
        ClubDto club = clubService.getClubInfo(form.getClubId());
        MemberDto requester = memberService.getMemberInfo(form.getClubId(), username);

        ClubStatusAccessPolicy.isActive(club);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        String imageKey=null;
        String title=null;
        String content=null;


        if(form.isTitleChanged()){
            title = form.getTitle();
        }
        if(form.isContentChanged()){
            content = form.getContent();
        }
        if(form.isImageChanged()){
            if(form.getImage()==null || form.getImage().isEmpty()){
                imageKey = "";
            }else{
                imageKey = s3Service.saveImage("club/" + club.getName()
                        + "/board/image", form.getTitle(), form.getImage());
            }
        }

        ChangePost command = new ChangePost(username, club.getClubId(), form.getPostId(), title, content, imageKey);

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }

    public void deletePost(String username, Long clubId, Long postId){
        ClubDto club = clubService.getClubInfo(clubId);
        MemberDto requester = memberService.getMemberInfo(clubId, username);

        ClubStatusAccessPolicy.isActive(club);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        DeletePost command = new DeletePost(username, clubId, postId);

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }


    public void createComment(String username, CommentForm form){
        ClubDto club = clubService.getClubInfo(form.getClubId());
        MemberDto requester = memberService.getMemberInfo(form.getClubId(), username);

        ClubStatusAccessPolicy.isActive(club);
        MemberStatusAccessPolicy.isActive(requester);

        CreateComment command = new CreateComment(username, club.getClubId(), form.getPostId(),
                form.getBaseId(), form.getContent(), requester.getMemberId(), requester.getName());

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }

    public void changeComment(String username, CommentChangeForm form){
        ClubDto club = clubService.getClubInfo(form.getClubId());
        MemberDto requester = memberService.getMemberInfo(form.getClubId(), username);

        ClubStatusAccessPolicy.isActive(club);
        MemberStatusAccessPolicy.isActive(requester);

        ChangeComment command = new ChangeComment(username, form.getClubId(), form.getCommentId(), form.getContent(), requester.getRoleRank()>1);

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }

    public void deleteComment(String username, Long clubId, Long commentId){
        ClubDto club = clubService.getClubInfo(clubId);
        MemberDto requester = memberService.getMemberInfo(clubId, username);

        ClubStatusAccessPolicy.isActive(club);
        MemberStatusAccessPolicy.isActive(requester);

        DeleteComment command = new DeleteComment(username, clubId, commentId, requester.getRoleRank()>1);

        kafkaProducerService.sendCommunityCommandToKafka(command);
    }
}
