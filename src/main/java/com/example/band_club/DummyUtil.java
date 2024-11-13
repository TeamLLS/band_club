package com.example.band_club;


import com.example.band_club.club.Club;
import com.example.band_club.club.ClubRepository;
import com.example.band_club.club.command.CreateClub;
import com.example.band_club.member.Member;
import com.example.band_club.member.MemberRepository;
import com.example.band_club.member.Role;
import com.example.band_club.member.command.CreateMember;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DummyUtil {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @PostConstruct
    public void makeDummy(){

        CreateClub commandC = new CreateClub();
        commandC.setName("DummyClub");
        commandC.setImageKey("common/club/default.png");
        commandC.setDescription("for test");

        Club club = clubRepository.save(new Club(commandC));


        String[] usernames = {"Dummy_userA", "Dummy_userB", "Dummy_userC", "Dummy_userD", "Dummy_userE"};
        String[] names = {"허연준", "임윤빈", "권미르", "최은", "하도준"};
        String[] genders = {"male", "female", "male", "female", "male"};
        Integer[] birthYears = {2003, 2002, 2003, 2003, 2004};
        Role[] roles = {Role.MANAGER, Role.OWNER, Role.REGULAR, Role.REGULAR, Role.REGULAR};

        CreateMember commandM;


        for(int i=0; i<5; i++){
            commandM = new CreateMember();
            commandM.setClubId(1L);
            commandM.setClub(club);
            commandM.setRole(roles[i]);
            commandM.setUsername(usernames[i]);
            commandM.setName(names[i]);
            commandM.setGender(genders[i]);
            commandM.setBirthYear(birthYears[i]);
            memberRepository.save(new Member(commandM));
            club.memberNumIncreased();
        }

    }



}
