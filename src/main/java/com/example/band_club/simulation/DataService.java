package com.example.band_club.simulation;


import com.example.band_club.club.Club;
import com.example.band_club.club.ClubStore;
import com.example.band_club.member.Member;
import com.example.band_club.member.MemberStore;
import com.example.band_club.simulation.command.CreateMemberDummy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DataService {

    private final ClubStore clubStore;
    private final MemberStore memberStore;

    public Long createMemberDummy(String username, CreateMemberDummy command){

        Club club = clubStore.find(command.getClubId());
        command.setClub(club);

        return memberStore.save(username, new Member(command)).getId();
    }


}
