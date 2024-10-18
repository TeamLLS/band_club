package com.example.band_club.policy;

import com.example.band_club.member.Member;
import com.example.band_club.member.Role;
import com.example.band_club.member.exception.InsufficientMemberException;
import com.example.band_club.member.form.MemberDto;

public class MemberRoleAccessPolicy {

    public static void isHigherThan(Member member, Role role){
        if(member.getRole().getRank() <= role.getRank()){
            throw new InsufficientMemberException("접근 권한 부족", member.getRole().getDisplay(), role.getDisplay());
        }
    }

    public static void isHigherThan(MemberDto member, Role role){
        if(member.getRoleRank() <= role.getRank()){
            throw new InsufficientMemberException("접근 권한 부족", member.getRoleName(), role.getDisplay());
        }
    }

}
