package com.example.band_club.club;

import com.example.band_club.club.command.ChangeClub;
import com.example.band_club.club.command.CreateClub;
import com.example.band_club.club.form.ClubDto;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.Role;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.policy.MemberRoleAccessPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

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

        Club newClub = new Club(command);

        Club saved = clubStore.save(username, newClub);

        memberService.registerOwner(username, new CreateMember(saved, username));

        return saved.getId();
    }

    @Transactional(readOnly = true)
    public ClubDto getClubInfo(Long clubId) {

        Club club = clubStore.find(clubId);

        String imageResource = s3Service.getProduction() + "/" + club.getImage();

        return new ClubDto(club, imageResource);
    }


    public void ChangeClubInfo(String username, ChangeClub command){

        Club club = clubStore.find(command.getClubId());
        MemberDto find = memberService.getMemberInfo(club.getId(), username);

        if(!MemberRoleAccessPolicy.checkMemberRole(find, Role.OWNER)){
            throw new RuntimeException();
        }

        String imageKey;
        if(command.isImageChanged()){
            if(command.getImage()==null || command.getImage().isEmpty()){
                imageKey = "common/club/default.png";
            }else{
                imageKey = s3Service.saveImage("club/" + command.getName() + "/image", "main", command.getImage());
            }

            command.setImageKey(imageKey);
        }

        club.changeInfo(command);
    }
}
