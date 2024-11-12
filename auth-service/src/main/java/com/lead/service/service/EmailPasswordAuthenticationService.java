package com.lead.service.service;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.dto.UserDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailPasswordAuthenticationService implements AuthenticationService {

    @Override
    public UserDto login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public UserDto register(RegistrationRequest registrationRequest) {
        return null;
    }

    @Override
    public TokenDto refreshToken(TokenRequest tokenRequest) {
        return null;
    }
}
