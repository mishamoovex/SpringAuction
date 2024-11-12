package com.lead.service.service.auth;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.exception.WrongCredentialsException;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.model.response.AuthResponse;
import com.lead.service.service.token.TokenService;
import com.lead.service.web.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        var token = getToken(registrationRequest.getEmail());
        return new AuthResponse(user, token);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authState = authenticationManager.authenticate(authToken);

        if (authState.isAuthenticated()) {
            var user = userServiceClient.getByEmail(loginRequest.getEmail());
            var token = getToken(loginRequest.getEmail());
            return new AuthResponse(user, token);
        } else throw new WrongCredentialsException("User with email " + loginRequest.getEmail() + " does not exist");
    }

    private TokenDto getToken(String email) {
        var accessToken = tokenService.generateAccessToken(email);
        var refreshToken = tokenService.generateRefreshToken(email);
        return new TokenDto(accessToken, refreshToken);
    }

    @Override
    public TokenDto refreshToken(TokenRequest tokenRequest) {
        return null;
    }
}
