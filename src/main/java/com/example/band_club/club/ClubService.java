package com.example.band_club.club;

import com.example.band_club.budget.BudgetService;
import com.example.band_club.club.command.ChangeClub;
import com.example.band_club.club.command.CreateClub;
import com.example.band_club.club.form.ClubDto;
import com.example.band_club.club.policy.ClubStatusAccessPolicy;
import com.example.band_club.external.kafka.KafkaProducerService;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.Role;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.member.policy.MemberRoleAccessPolicy;
import com.example.band_club.member.policy.MemberStatusAccessPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final BudgetService budgetService;
    private final MemberService memberService;
    private final ClubStore clubStore;
    private final S3Service s3Service;



    public Long createClub(String username, CreateClub command){

        String imageKey;

        if(command.getImage()==null || command.getImage().isEmpty()){
            imageKey = "common/club/default.png";
        }else{
            imageKey = s3Service.saveImage("club/" + command.getName() + "/image", "main", command.getImage());
        }

        command.setImageKey(imageKey);

        Club saved = clubStore.save(username, new Club(command));

        memberService.registerOwner(username, new CreateMember(username,saved));

        budgetService.createBudget(saved.getId(), username);

        return saved.getId();
    }

    @Transactional(readOnly = true)
    public ClubDto getClubInfo(Long clubId) {

        Club club = clubStore.find(clubId);

        String imageResource = s3Service.getProduction() + "/" + club.getImage();

        return new ClubDto(club, imageResource);
    }


    public Long changeClubInfo(String username, ChangeClub command){

        Club club = clubStore.find(command.getClubId());
        MemberDto requester = memberService.getMemberInfo(club.getId(), username);

        ClubStatusAccessPolicy.isActive(club);
        MemberStatusAccessPolicy.isActive(requester);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.MANAGER);

        String imageKey;
        if(command.isImageChanged()){
            if(command.getImage()==null || command.getImage().isEmpty()){
                imageKey = "common/club/default.png";
            }else{
                imageKey = s3Service.saveImage("club/" + command.getName() + "/image", "main", command.getImage());
            }

            command.setImageKey(imageKey);
        }

        clubStore.saveEvent(club.changeInfo(username, command));

        return club.getId();
    }


    public Long closeClub(String username, Long clubId){
        Club club = clubStore.find(clubId);
        MemberDto requester = memberService.getMemberInfo(clubId, username);

        ClubStatusAccessPolicy.isActive(club);
        MemberStatusAccessPolicy.isActive(requester);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.MANAGER);

        budgetService.closeBudget(clubId, username);

        clubStore.saveEvent(club.close(username));

        return club.getId();
    }
}
