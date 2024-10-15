package com.example.band_club.club;

import com.example.band_club.club.command.CreateClub;
import com.example.band_club.club.form.ClubResponseForm;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.command.CreateMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final MemberService memberService;
    private final ClubStore clubStore;
    private final S3Service s3Service;

    @Transactional
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
    public ClubResponseForm getClubInfo(Long clubId) {

        Club club = clubStore.find(clubId);

        String imageResource = s3Service.getProduction() + "/" + club.getImage();

        return new ClubResponseForm(club, imageResource);
    }

}
