package com.lead.service.service.auth;

import com.lead.common.security.web.UserDetailsClient;
import com.lead.core.exception.NotFoundException;
import com.lead.service.exception.WrongCredentialsException;
import com.lead.service.model.dto.AuthResponseDto;
import com.lead.service.model.dto.AuthTokenType;
import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.dto.UserDto;
import com.lead.service.model.request.LoginRequest;
import com.lead.service.model.request.RegistrationRequest;
import com.lead.service.model.request.TokenRequest;
import com.lead.service.service.token.TokenService;
import com.lead.service.web.UserServiceClient;
import io.jsonwebtoken.ExpiredJwtException;
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
    private UserDetailsClient userDetailsClient;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    @Override
    public AuthResponseDto register(RegistrationRequest registrationRequest) {
        var userDetails = userServiceClient.save(registrationRequest);
        var token = getToken(registrationRequest.getEmail());
        var user = modelMapper.map(userDetails, UserDto.class);
        return new AuthResponseDto(user, token);
    }

    @Override
    public AuthResponseDto login(LoginRequest loginRequest) {
        var authState = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (authState.isAuthenticated()) {
            var userDetails = userDetailsClient.getByEmail(loginRequest.getEmail());
            var user = modelMapper.map(userDetails, UserDto.class);
            var token = getToken(loginRequest.getEmail());
            return new AuthResponseDto(user, token);
        }
        throw new WrongCredentialsException("User with email " + loginRequest.getEmail() + " does not exist");
    }

    @Override
    public String refreshToken(TokenRequest tokenRequest) {
        try {
            var tokenDetails = tokenService.parse(tokenRequest.getRefreshToken());
            var isTokenValid = !tokenDetails.getIsExpired()
                    && tokenDetails.getUsername() != null
                    && tokenDetails.getAuthTokenType() == AuthTokenType.REFRESH;

            if (isTokenValid) {
                var userDetails = userDetailsClient.getByEmail(tokenDetails.getUsername());
                if (userDetails != null) return tokenService.generateAccessToken(userDetails.getEmail());
            }

            throw new WrongCredentialsException("Invalid refresh token");
        } catch (ExpiredJwtException | NotFoundException e) {
            throw new WrongCredentialsException(e.getMessage());
        }
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
