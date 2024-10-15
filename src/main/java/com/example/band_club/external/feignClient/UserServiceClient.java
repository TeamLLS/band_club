package com.example.band_club.external.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-client", url = "${band.user.url}")
public interface UserServiceClient {

    @GetMapping("/user/profile/{username}/simple")
    UserProfile getProfile(@PathVariable String username);
}
