package com.example.band_club.policy;

import com.example.band_club.member.Member;
import com.example.band_club.member.Role;
import com.example.band_club.member.form.MemberDto;

public class MemberRoleAccessPolicy {

    public static boolean checkMemberRole(Member member, Role role){
        return member.getRole().getRank()<= role.getRank();
    }

    public static boolean checkMemberRole(MemberDto member, Role role){
        return member.getRoleRank()<=role.getRank();
    }
}
