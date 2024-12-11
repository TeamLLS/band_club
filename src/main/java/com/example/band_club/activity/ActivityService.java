package com.example.band_club.activity;


import com.example.band_club.activity.command.*;
import com.example.band_club.activity.form.ActivityForm;
import com.example.band_club.club.ClubService;
import com.example.band_club.club.form.ClubDto;
import com.example.band_club.club.policy.ClubStatusAccessPolicy;
import com.example.band_club.external.kafka.KafkaProducerService;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.Role;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.member.policy.MemberRoleAccessPolicy;
import com.example.band_club.member.policy.MemberStatusAccessPolicy;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {

    private final ClubService clubService;
    private final MemberService memberService;
    private final S3Service s3Service;
    private final KafkaProducerService kafkaProducerService;

    public void create(String username, ActivityForm form){
        ClubDto club = clubService.getClubInfo(form.getClubId());
        MemberDto member = memberService.getMemberInfo(form.getClubId(), username);


        ClubStatusAccessPolicy.isActive(club);
        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);


        String imageKey;
        if(form.getImage()==null || form.getImage().isEmpty()){
            imageKey = "common/activity/default.png";
        }else{
            imageKey = s3Service.saveImage("club/" + club.getName()
                    + "/activity/" + form.getName() + "/image", "main", form.getImage());
        }

        if(form.getContactInfo()==null){
            form.setContactInfo(club.getContactInfo());
        }

        OpenActivity command = new OpenActivity(username, club.getClubId(), form.getName(), imageKey, form.getLocation(),
                form.getDescription(), form.getStartTime(), form.getEndTime(), form.getDeadline(), form.getContactInfo());

        kafkaProducerService.sendActivityCommandToKafka(command);
    }

    public void close(String username, Long clubId, Long activityId){
        MemberDto member = memberService.getMemberInfo(clubId, username);
        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        CloseActivity command = new CloseActivity(username, activityId, clubId);
        kafkaProducerService.sendActivityCommandToKafka(command);
    }

    public void cancel(String username, Long clubId, Long activityId){
        MemberDto member = memberService.getMemberInfo(clubId, username);
        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        CancelActivity command = new CancelActivity(username, activityId, clubId);
        kafkaProducerService.sendActivityCommandToKafka(command);
    }

    public void attend(String username, Long activityId, Long clubId){
        MemberDto member = memberService.getMemberInfo(clubId, username);
        MemberStatusAccessPolicy.isActive(member);

        AttendActivity command = new AttendActivity(username, activityId, clubId, member.getMemberId(), member.getName(), null, null);
        kafkaProducerService.sendActivityCommandToKafka(command);
    }

    public void additionalAttend(String username, Long activityId, Long clubId , String target) {
        MemberDto requester = memberService.getMemberInfo(clubId, username);
        MemberDto member = memberService.getMemberInfo(clubId, target);

        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);
        MemberStatusAccessPolicy.isActive(member);

        AttendActivity command = new AttendActivity(username, activityId, clubId, member.getMemberId(), member.getName(), true, member.getUsername());
        kafkaProducerService.sendActivityCommandToKafka(command);
    }


    public void notAttend(String username, Long activityId, Long clubId){
        MemberDto member = memberService.getMemberInfo(clubId, username);
        MemberStatusAccessPolicy.isActive(member);

        NotAttendActivity command = new NotAttendActivity(username, activityId, member.getMemberId(), null);
        kafkaProducerService.sendActivityCommandToKafka(command);
    }

    public void additionalNotAttend(String username, Long activityId, Long clubId , String target) {
        MemberDto requester = memberService.getMemberInfo(clubId, username);
        MemberDto member = memberService.getMemberInfo(clubId, target);

        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);
        MemberStatusAccessPolicy.isActive(member);

        NotAttendActivity command = new NotAttendActivity(username, activityId, member.getMemberId(), true);
        kafkaProducerService.sendActivityCommandToKafka(command);
    }
}
