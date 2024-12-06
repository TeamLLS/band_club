package com.example.band_club.member;


import com.example.band_club.club.Club;
import com.example.band_club.club.policy.ClubStatusAccessPolicy;
import com.example.band_club.external.feignClient.UserProfile;
import com.example.band_club.external.feignClient.UserServiceClient;
import com.example.band_club.external.s3.S3Service;
import com.example.band_club.member.command.ChangeMemberRole;
import com.example.band_club.member.command.CreateMember;
import com.example.band_club.member.exception.DuplicatedMemberException;
import com.example.band_club.member.form.MemberClubItemDto;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.member.policy.MemberRoleAccessPolicy;
import com.example.band_club.member.policy.MemberStatusAccessPolicy;
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
    private final MemberStore memberStore;



    public Long registerOwner(String username, CreateMember command){
        command.setRole(Role.OWNER);
        return createMember(username, command);
    }

    public Long registerRegular(String username, CreateMember command){

        Member requester = memberStore.findMemberByUsername(command.getClubId(), username);

        MemberStatusAccessPolicy.isActive(requester);
        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);

        Club club = requester.getClub();
        ClubStatusAccessPolicy.isActive(club);

        command.setClub(club);
        command.setRole(Role.REGULAR);

        return createMember(username, command);
    }

    private Long createMember(String username, CreateMember command){

        if(memberStore.existMemberByUsername(command.getClubId(), command.getUsername())){
            throw new DuplicatedMemberException("이미 존재하는 회원", command.getClubId(), command.getUsername());
        }

        UserProfile profile = userServiceClient.getProfile(command.getUsername());
        command.setProfile(profile);

        Member saved = memberStore.save(username, new Member(command));
        command.getClub().memberNumIncreased();

        return saved.getId();
    }

    @Transactional(readOnly = true)
    public List<MemberClubItemDto> getMemberClubList(String username, int pageNo, int pageSize){
        return memberStore.findMemberClubListByUsername(username, pageNo, pageSize).getContent().stream()
                .map(m -> new MemberClubItemDto(m, s3Service.getProduction() + "/" + m.getClub().getImage())).toList();
    }


    @Transactional(readOnly = true)
    public MemberDto getMemberInfo(Long clubId, String username){
        Member find = memberStore.findMemberByUsername(clubId, username);
        return new MemberDto(find);
    }

    @Transactional(readOnly = true)
    public MemberDto getMemberInfo(Long memberId){
        Member find = memberStore.findMemberById(memberId);
        return new MemberDto(find);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> getMemberList(Long clubId, int pageNo, int pageSize){
        return memberStore.findMemberListByClubId(clubId, pageNo, pageSize).getContent()
                .stream().map(MemberDto::new).toList();
    }

    @Transactional(readOnly = true)
    public List<MemberDto> getSpecificMemberList(List<Integer> list){
        return memberStore.findSpecificList(list).stream().map(MemberDto::new).toList();
    }


    public Long changeMemberRole(String username, ChangeMemberRole command){
        Member member = memberStore.find(command.getMemberId());

        Member requester = memberStore.findMemberByUsername(member.getClub().getId(), username);

        MemberStatusAccessPolicy.isActive(requester);
        MemberRoleAccessPolicy.isHigherThan(requester, member.getRole());

        memberStore.saveEvent(member.changeRole(username, command));

        return member.getId();
    }

    public Long withdrawMember(String username, Long clubId){
        Member member = memberStore.findMemberByUsername(clubId, username);
        member.getClub().memberNumDecreased();
        memberStore.saveEvent(member.left());
        return member.getId();
    }

    public Long banMember(String username, Long memberId){
        Member member = memberStore.find(memberId);
        Member requester = memberStore.findMemberByUsername(member.getClub().getId(), username);
        member.getClub().memberNumDecreased();

        MemberStatusAccessPolicy.isActive(requester);
        MemberRoleAccessPolicy.isHigherThan(requester, member.getRole());

        memberStore.saveEvent(member.ban(username));
        return member.getId();
    }
}
