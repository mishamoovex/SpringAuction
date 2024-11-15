package com.lead.service.auth.controller;

import com.lead.service.auth.model.dto.AuthResponseDto;
import com.lead.service.auth.model.request.LoginRequest;
import com.lead.service.auth.model.request.RegistrationRequest;
import com.lead.service.auth.model.request.TokenRequest;
import com.lead.service.auth.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/token")
    public ResponseEntity<String> refreshToken(@RequestBody @Valid TokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
