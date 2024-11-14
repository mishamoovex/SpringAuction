package com.lead.service.auth.service.auth;

import com.lead.service.auth.model.dto.AuthResponseDto;
import com.lead.service.auth.model.request.LoginRequest;
import com.lead.service.auth.model.request.RegistrationRequest;
import com.lead.service.auth.model.request.TokenRequest;

public interface AuthenticationService {
    AuthResponseDto login(LoginRequest loginRequest);

    AuthResponseDto register(RegistrationRequest registrationRequest);

    String refreshToken(TokenRequest tokenRequest);
}
