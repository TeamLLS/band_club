package com.example.band_club.member;

import com.example.band_club.member.event.MemberCreated;
import com.example.band_club.member.event.MemberEventJpo;
import com.example.band_club.member.event.MemberEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberStore {

    private final MemberRepository memberRepository;
    private final MemberEventRepository memberEventRepository;

    @Transactional
    public void save(String username, Member member){
        Member saved = memberRepository.save(member);

        MemberCreated createdEvent = new MemberCreated(username, saved);

        memberEventRepository.save(new MemberEventJpo(createdEvent));
    }

    @Transactional(readOnly = true)
    public Member findMemberByUsername(Long clubId, String username){
        return memberRepository.findByClubIdAndUsername(clubId, username).orElseThrow();
    }


    @Transactional(readOnly = true)
    public List<Member> findClubMemberListByUsername(String username, int pageNo){

        Pageable pageable = PageRequest.of(pageNo, 2);

        return memberRepository.findAllWithClubByUsername(username, pageable).getContent();
    }
}
