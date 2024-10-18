package com.example.band_club.policy;

import com.example.band_club.member.Member;
import com.example.band_club.member.MemberStatus;
import com.example.band_club.member.exception.NotActiveMemberException;
import com.example.band_club.member.form.MemberDto;

public class MemberStatusAccessPolicy {

    public static void isActive(Member member){
        if(member.getStatus() != MemberStatus.ACTIVE){
            throw new NotActiveMemberException("활성 멤버 아님", member.getId(), member.getStatus().getDisplay());
        }
    }

    public static void isActive(MemberDto member){
        if(member.getStatus() != MemberStatus.ACTIVE){
            throw new NotActiveMemberException("활성 멤버 아님", member.getMemberId(), member.getStatus().getDisplay());
        }
    }
}
