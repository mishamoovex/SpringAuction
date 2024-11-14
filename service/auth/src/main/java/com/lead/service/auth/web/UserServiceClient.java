package com.lead.service.auth.web;

import com.lead.security.model.AuthUserDetails;
import com.lead.service.auth.model.request.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8082/v1/user")
public interface UserServiceClient {

    @PostMapping
    AuthUserDetails save(@RequestBody RegistrationRequest request);
}
