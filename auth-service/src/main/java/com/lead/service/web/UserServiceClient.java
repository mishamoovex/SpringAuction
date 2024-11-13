package com.lead.service.web;

import com.lead.service.model.dto.UserDetailsDto;
import com.lead.service.model.request.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8082/v1/user")
public interface UserServiceClient {

    @PostMapping
    UserDetailsDto save(@RequestBody RegistrationRequest request);

    @GetMapping("/account")
    UserDetailsDto getByEmail(@RequestParam String email);
}
