package com.example.band_club.club;


import com.example.band_club.club.event.*;
import com.example.band_club.external.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//@Transactional
@RequiredArgsConstructor
public class ClubStore {

    private final KafkaProducerService kafkaProducerService;
    private final ClubRepository clubRepository;
    private final ClubEventRepository clubEventRepository;



    public Club save(String username, Club club){
        Club saved = clubRepository.save(club);
        saveEvent(new ClubCreated(username, saved));
        return saved;
    }

    //@Transactional(readOnly = true)
    public Club find(Long clubId){
        return clubRepository.findById(clubId).orElseThrow();
    }


    public ClubEventJpo saveEvent(ClubEvent event){
        ClubEventJpo saved = clubEventRepository.save(new ClubEventJpo(event));

        if(!(event instanceof ClubChanged)){
            kafkaProducerService.sendClubEventToKafka(event);
        }

        return saved;
    }
}
