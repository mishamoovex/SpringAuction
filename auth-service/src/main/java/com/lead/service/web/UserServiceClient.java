package com.lead.service.web;

import com.lead.service.model.dto.UserDto;
import com.lead.service.model.request.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8082/v1/user")
public interface UserServiceClient {

    @PostMapping
    UserDto save(@RequestBody RegistrationRequest request);

    @GetMapping("/findByEmail/{email}")
    UserDto getByEmail(@PathVariable  String email);
}
