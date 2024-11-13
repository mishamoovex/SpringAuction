package com.lead.service.service.token;

import com.lead.service.model.dto.AuthTokenDetails;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    AuthTokenDetails parse(String token) throws ExpiredJwtException;
}
