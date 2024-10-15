package com.example.band_club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BandClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BandClubApplication.class, args);
    }

}
