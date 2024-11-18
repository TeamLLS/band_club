package com.example.band_club.member;


import com.example.band_club.external.kafka.KafkaProducerService;
import com.example.band_club.member.event.*;
import com.example.band_club.member.exception.NotMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberStore {


    private final KafkaProducerService kafkaProducerService;
    private final MemberRepository memberRepository;
    private final MemberEventRepository memberEventRepository;


    public Member save(String username, Member member){
        Member saved = memberRepository.save(member);
        saveEvent(new MemberCreated(username, saved));
        return saved;
    }

    public Member findMemberByUsername(Long clubId, String username){
        return memberRepository.findByClubIdAndUsername(clubId, username)
                .orElseThrow(()->new NotMemberException("회원 아님", clubId, username));
    }

    public boolean existMemberByUsername(Long clubId, String username){
        return memberRepository.existsByClubIdAndUsername(clubId, username);
    }


    public Page<Member> findMemberClubListByUsername(String username, int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return memberRepository.findAllWithClubByUsername(username, pageable);
    }

    public Page<Member> findMemberListByClubId(Long clubId, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return memberRepository.findAllByClubId(clubId, pageable);
    }

    public List<Member> findSpecificList(List<Integer> list){
        return memberRepository.findSpecifics(list);
    }

    public Member find(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    public MemberEventJpo saveEvent(MemberEvent event){

        MemberEventJpo saved = memberEventRepository.save(new MemberEventJpo(event));

        if(!(event instanceof MemberRoleChanged)){
            kafkaProducerService.sendMemberEventToKafka(event);
        }

        return saved;
    }
}
