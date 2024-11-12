package com.lead.service.service.auth;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.model.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailPasswordAuthenticationService implements AuthenticationService {

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthResponse register(RegistrationRequest registrationRequest) {
        return null;
    }

    @Override
    public TokenDto refreshToken(TokenRequest tokenRequest) {
        return null;
    }
}
