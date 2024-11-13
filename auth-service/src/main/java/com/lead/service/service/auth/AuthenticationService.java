package com.lead.service.service.auth;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.model.dto.AuthResponseDto;

public interface AuthenticationService {
    AuthResponseDto login(LoginRequest loginRequest);

    AuthResponseDto register(RegistrationRequest registrationRequest);

    TokenDto refreshToken(TokenRequest tokenRequest);
}
