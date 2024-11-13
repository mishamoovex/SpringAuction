package com.lead.service.service.auth;

import com.lead.service.model.dto.AuthResponseDto;
import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.dto.UserDto;
import com.lead.service.exception.WrongCredentialsException;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.service.token.TokenService;
import com.lead.service.web.UserServiceClient;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailPasswordAuthenticationService implements AuthenticationService {

    private UserServiceClient userServiceClient;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    @Override
    public AuthResponseDto register(RegistrationRequest registrationRequest) {
        var userDetails = userServiceClient.save(registrationRequest);
        var authState = authenticate(registrationRequest.getEmail(), registrationRequest.getPassword());
        if (authState.isAuthenticated()) {
            var token = getToken(registrationRequest.getEmail());
            var user = modelMapper.map(userDetails, UserDto.class);
            return new AuthResponseDto(user, token);
        }
        throw new WrongCredentialsException("Failed to authenticate newly registered user");
    }

    @Override
    public AuthResponseDto login(LoginRequest loginRequest) {
        var authState = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (authState.isAuthenticated()) {
            var userDetails = userServiceClient.getByEmail(loginRequest.getEmail());
            var user = modelMapper.map(userDetails, UserDto.class);
            var token = getToken(loginRequest.getEmail());
            return new AuthResponseDto(user, token);
        }
        throw new WrongCredentialsException("User with email " + loginRequest.getEmail() + " does not exist");
    }

    @Override
    public String refreshToken(TokenRequest tokenRequest) {
        String username = tokenService.extractUsername(tokenRequest.getRefreshToken());
        boolean isExpired = tokenService.isTokenExpired(tokenRequest.getRefreshToken());

        if (username != null && !isExpired) {
            var userDetails = userServiceClient.getByEmail(username);
            if (userDetails != null) {
                return tokenService.generateAccessToken(userDetails.getId());
            }
        }

        throw new WrongCredentialsException("Invalid refresh token");
    }

    private Authentication authenticate(String email, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authToken);
    }

    private TokenDto getToken(String email) {
        var accessToken = tokenService.generateAccessToken(email);
        var refreshToken = tokenService.generateRefreshToken(email);
        return new TokenDto(accessToken, refreshToken);
    }
}
