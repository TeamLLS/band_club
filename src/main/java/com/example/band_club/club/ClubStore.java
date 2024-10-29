package com.example.band_club.club;


import com.example.band_club.club.event.ClubCreated;
import com.example.band_club.club.event.ClubEventJpo;
import com.example.band_club.club.event.ClubEventRepository;
import com.example.band_club.club.event.ClubEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//@Transactional
@RequiredArgsConstructor
public class ClubStore {

    private final ClubRepository clubRepository;
    private final ClubEventRepository clubEventRepository;



    public Club save(String username, Club club){
        Club saved = clubRepository.save(club);
        ClubEvent clubCreated = new ClubCreated(username, saved);
        clubEventRepository.save(new ClubEventJpo(clubCreated));
        return saved;
    }

    //@Transactional(readOnly = true)
    public Club find(Long clubId){
        return clubRepository.findById(clubId).orElseThrow();
    }


    public void saveEvent(ClubEvent event){
        clubEventRepository.save(new ClubEventJpo(event));
    }
}
