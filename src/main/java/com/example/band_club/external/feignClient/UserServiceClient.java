package com.example.band_club.external.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-client", url = "${band.user.url}")
public interface UserServiceClient {

    @GetMapping("/user/profile/simple")
    UserProfile getProfile(@RequestParam("username") String username);
}
