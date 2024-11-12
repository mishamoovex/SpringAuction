package com.lead.service.service.auth;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.model.response.AuthResponse;
import com.lead.service.service.token.TokenService;
import com.lead.service.web.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailPasswordAuthenticationService implements AuthenticationService {

    private UserServiceClient userServiceClient;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    //TODO do I need to call authenticate after registration???
    @Override
    public AuthResponse register(RegistrationRequest registrationRequest) {
        var user = userServiceClient.save(registrationRequest);
        var token = new TokenDto(
                tokenService.generateAccessToken(user.getEmail()),
                tokenService.generateRefreshToken(user.getEmail())
        );
        return new AuthResponse(user, token);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public TokenDto refreshToken(TokenRequest tokenRequest) {
        return null;
    }
}
