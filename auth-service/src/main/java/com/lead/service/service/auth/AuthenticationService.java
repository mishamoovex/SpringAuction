package com.lead.service.service.auth;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.dto.UserDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;

public interface AuthenticationService {
    UserDto login(LoginRequest loginRequest);

    UserDto register(RegistrationRequest registrationRequest);

    TokenDto refreshToken(TokenRequest tokenRequest);
}
