package com.lead.service.web;

import com.lead.service.model.dto.UserAccountDto;
import com.lead.service.model.request.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8082/v1/user")
public interface UserServiceClient {

    @PostMapping
    UserAccountDto save(@RequestBody RegistrationRequest request);

    @GetMapping("/account")
    UserAccountDto getByEmail(@RequestParam String email);
}
