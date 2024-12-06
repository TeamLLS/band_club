package com.example.band_club.budget;


import com.example.band_club.budget.command.*;
import com.example.band_club.budget.form.BudgetRecordForm;
import com.example.band_club.budget.form.MemberRegisterForm;
import com.example.band_club.budget.form.PayBookForm;
import com.example.band_club.external.kafka.KafkaProducerService;
import com.example.band_club.member.MemberService;
import com.example.band_club.member.Role;
import com.example.band_club.member.form.MemberDto;
import com.example.band_club.member.policy.MemberRoleAccessPolicy;
import com.example.band_club.member.policy.MemberStatusAccessPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {


    private final MemberService memberService;
    private final KafkaProducerService kafkaProducerService;

    public void createBudget(Long clubId, String username){
        kafkaProducerService.sendBudgetCommandToKafka(new CreateBudget(username, clubId));
    }

    public void closeBudget(Long clubId, String username){
        kafkaProducerService.sendBudgetCommandToKafka(new CloseBudget(username, clubId));
    }

    public void updateBudget(String username, BudgetRecordForm form){
        MemberDto member = memberService.getMemberInfo(form.getClubId(), username);
        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        kafkaProducerService.sendBudgetCommandToKafka(new UpdateBudget(username, form.getClubId(), form.getDescription(), form.getAmount()));
    }

    public void createPayBook(String username, PayBookForm form){
        MemberDto member = memberService.getMemberInfo(form.getClubId(), username);

        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        kafkaProducerService.sendBudgetCommandToKafka(new CreatePayBook(username, form.getClubId(), member.getName(), form.getAmount(), form.getName(), form.getDescription(), form.getDeadline()));

    }

    public void cancelPayBook(String username, Long clubId, Long payBookId){

        MemberDto member = memberService.getMemberInfo(clubId, username);

        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        kafkaProducerService.sendBudgetCommandToKafka(new CancelPayBook(username, payBookId, clubId));
    }

    public void closePayBook(String username, Long clubId, Long payBookId){

        MemberDto member = memberService.getMemberInfo(clubId, username);

        MemberRoleAccessPolicy.isHigherThan(member, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(member);

        kafkaProducerService.sendBudgetCommandToKafka(new ClosePayBook(username, payBookId, clubId));
    }

    public void registerAll(String username, Long clubId, Long payBookId){

        MemberDto requester = memberService.getMemberInfo(clubId, username);

        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        List<MemberDto> members;
        int pageNo=0;

        do{
            members = memberService.getMemberList(clubId, pageNo, 10);
            for (MemberDto member : members) {
                registerPayMember(username, payBookId, member);
            }
            pageNo++;
        }while (!members.isEmpty());
    }

    public void registerSpecificMembers(String username, MemberRegisterForm form){

        MemberDto requester = memberService.getMemberInfo(form.getClubId(), username);

        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        List<Integer> list = form.getList();
        Long payBookId = form.getPayBookId();

        List<MemberDto> members;
        int size = 10;

        for(int i=0; i<list.size(); i+=size){

            if(i+size>list.size()){
                members = memberService.getSpecificMemberList(list.subList(i,list.size()));
            }else{
                members = memberService.getSpecificMemberList(list.subList(i, i+size));
            }

            for (MemberDto member : members) {
                registerPayMember(username, payBookId, member);
            }
        }
    }

    private void registerPayMember(String username, Long payBookId, MemberDto member){
        kafkaProducerService.sendBudgetCommandToKafka(new RegisterPayMember(username, payBookId, member.getClubId(),
                member.getMemberId(), member.getName(), member.getUsername()));
    }

    public void changePayMemberStatus(String username, Long payBookId, Long memberId, String status, Instant time){
        MemberDto target = memberService.getMemberInfo(memberId);
        MemberDto requester = memberService.getMemberInfo(target.getClubId(), username);

        MemberRoleAccessPolicy.isHigherThan(requester, Role.REGULAR);
        MemberStatusAccessPolicy.isActive(requester);

        kafkaProducerService.sendBudgetCommandToKafka(new UpdatePayMember(username, payBookId, memberId, status, time));
    }
}
