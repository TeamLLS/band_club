package com.example.band_club.member;


import com.example.band_club.member.event.MemberCreated;
import com.example.band_club.member.event.MemberEvent;
import com.example.band_club.member.event.MemberEventJpo;
import com.example.band_club.member.event.MemberEventRepository;
import com.example.band_club.member.exception.NotMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Transactional
@RequiredArgsConstructor
public class MemberStore {

    private final MemberRepository memberRepository;
    private final MemberEventRepository memberEventRepository;


    public Member save(String username, Member member){
        Member saved = memberRepository.save(member);

        MemberCreated createdEvent = new MemberCreated(username, saved);

        memberEventRepository.save(new MemberEventJpo(createdEvent));

        return saved;
    }

    //@Transactional(readOnly = true)
    public Member findMemberByUsername(Long clubId, String username){
        return memberRepository.findByClubIdAndUsername(clubId, username)
                .orElseThrow(()->new NotMemberException("회원 아님", clubId, username));
    }

    public boolean existMemberByUsername(Long clubId, String username){
        return memberRepository.existsByClubIdAndUsername(clubId, username);
    }


    //@Transactional(readOnly = true)
    public List<Member> findMemberClubListByUsername(String username, int pageNo){

        Pageable pageable = PageRequest.of(pageNo, 2);

        return memberRepository.findAllWithClubByUsername(username, pageable).getContent();
    }

    //@Transactional(readOnly = true)
    public List<Member> findMemberListByClubId(Long clubId, int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 2);

        return memberRepository.findAllByClubId(clubId, pageable).getContent();
    }

    //@Transactional(readOnly = true)
    public Member find(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    public void saveMemberEvent(MemberEvent event){
        memberEventRepository.save(new MemberEventJpo(event));
    }
}
