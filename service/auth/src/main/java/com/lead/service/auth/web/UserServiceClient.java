package com.lead.service.auth.web;

import com.lead.security.model.AuthUserDetailsDto;
import com.lead.service.auth.model.request.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-service",
        contextId = "user-service-authentication",
        path = "/v1/user"
)
public interface UserServiceClient {

    @PostMapping
    AuthUserDetailsDto save(@RequestBody RegistrationRequest request);
}
