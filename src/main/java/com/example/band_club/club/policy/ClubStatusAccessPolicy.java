package com.example.band_club.club.policy;

import com.example.band_club.club.Club;
import com.example.band_club.club.ClubStatus;
import com.example.band_club.club.exception.NotActiveClubException;
import com.example.band_club.club.form.ClubDto;

public class ClubStatusAccessPolicy {

    public static void isActive(Club club){
        if(club.getStatus() == ClubStatus.CLOSED){
            throw new NotActiveClubException("활성 클럽 아님", club.getId(), club.getStatus().getDisplay());
        }
    }

    public static void isActive(ClubDto club){
        if(club.getStatus().equals(ClubStatus.CLOSED.getDisplay())){
            throw new NotActiveClubException("활성 클럽 아님", club.getClubId(), club.getStatus());
        }
    }

}
