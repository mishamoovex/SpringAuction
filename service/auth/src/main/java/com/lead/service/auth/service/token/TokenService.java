package com.lead.service.auth.service.token;

import com.lead.service.auth.model.dto.AuthTokenDetails;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    AuthTokenDetails parse(String token) throws ExpiredJwtException;
}
