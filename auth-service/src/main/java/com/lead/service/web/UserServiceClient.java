package com.lead.service.web;

import com.lead.service.model.dto.UserDto;
import com.lead.service.model.request.RegistrationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8082/v1/user")
@Validated
public interface UserServiceClient {

    @PostMapping
    UserDto save(@RequestBody @Valid RegistrationRequest request);

    @GetMapping("/findByEmail/{email}")
    UserDto getByEmail(@PathVariable @Email String email);
}
