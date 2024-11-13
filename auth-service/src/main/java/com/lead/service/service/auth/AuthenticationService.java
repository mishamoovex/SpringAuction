package com.lead.service.service.auth;

import com.lead.service.model.dto.AuthResponseDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;

public interface AuthenticationService {
    AuthResponseDto login(LoginRequest loginRequest);

    AuthResponseDto register(RegistrationRequest registrationRequest);

    String refreshToken(TokenRequest tokenRequest);
}
