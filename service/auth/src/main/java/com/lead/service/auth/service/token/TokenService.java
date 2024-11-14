package com.lead.service.auth.service.token;

public interface TokenService {
    String generateAccessToken(String username);

    String generateRefreshToken(String username);
}
