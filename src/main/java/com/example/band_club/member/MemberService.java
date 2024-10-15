package com.example.band_club.member;


import com.example.band_club.club.Club;
import com.example.band_club.club.ClubStore;
import com.example.band_club.external.feignClient.UserProfile;
import com.example.band_club.external.feignClient.UserServiceClient;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.form.ClubMemberItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final UserServiceClient userServiceClient;
    private final S3Service s3Service;
    private final ClubStore clubStore;
    private final MemberStore memberStore;



    public void registerOwner(String username, CreateMember command){
        command.setRole(Role.OWNER);
        createMember(username, command);
    }

    public void registerRegular(String username, CreateMember command){

        Member requester = memberStore.findByClubIdAndUsername(command.getClubId(), username);
        if(requester.getRole().getRank()<2){
            throw new RuntimeException();
        }
        Club targetClub = clubStore.find(command.getClubId());
        command.setCLub(targetClub);
        command.setRole(Role.REGULAR);

        createMember(username, command);
    }

    private void createMember(String username, CreateMember command){

        UserProfile profile = userServiceClient.getProfile(command.getUsername());
        command.setProfile(profile);

        Member newMember = new Member(command);
        memberStore.save(username, newMember);
        command.getClub().memberNumIncreased();
    }

    @Transactional(readOnly = true)
    public List<ClubMemberItemForm> findClubMemberList(String username, int pageNo){
        return memberStore.findListWithClubByUsername(username, pageNo).stream()
                .map(m -> new ClubMemberItemForm(m, s3Service.getProduction() + "/" + m.getClub().getImage())).toList();
    }
}
