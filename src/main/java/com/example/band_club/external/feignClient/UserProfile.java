package com.example.band_club.external.feignClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private String username;
    private String name;
    private Integer birthYear;
    private String gender;
}
