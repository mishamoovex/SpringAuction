package com.lead.service.service.token;

public interface TokenService {
    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    boolean isTokenExpired(String token);

    String extractUsername(String token);
}
