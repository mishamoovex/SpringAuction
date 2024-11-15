package com.lead.security.web;

import com.lead.security.model.AuthUserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-service",
        contextId = "user-service-security",
        path = "/v1/user"
)
public interface UserDetailsClient {

    @GetMapping("/account")
    AuthUserDetailsDto getByEmail(@RequestParam String email);
}
