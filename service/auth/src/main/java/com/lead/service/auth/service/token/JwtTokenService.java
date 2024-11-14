package com.lead.service.auth.service.token;

import com.lead.security.model.AuthTokenType;
import com.lead.security.service.token.secret.SecretKeyService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
class JwtTokenService implements TokenService {

    private static final String CLAIM_KEY_TOKEN_TYPE = "token_type";

    @Value("${jwt.expiration.access}")
    private Long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshExpiration;

    private final Clock clock;
    private final SecretKeyService secretKeyService;

    public JwtTokenService(Clock clock, SecretKeyService secretKeyService) {
        this.clock = clock;
        this.secretKeyService = secretKeyService;
    }

    @Override
    public String generateAccessToken(String username) {
        return generateToken(username, accessExpiration, AuthTokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(String username) {
        return generateToken(username, refreshExpiration, AuthTokenType.REFRESH);
    }

    private String generateToken(String username, Long expiration, AuthTokenType tokenType) {
        var timestamp = LocalDateTime.now(clock);
        var expirationDate = timestamp.plusSeconds(expiration);

        return Jwts.builder()
                .subject(username)
                .claim(CLAIM_KEY_TOKEN_TYPE, tokenType.name())
                .issuedAt(toLegacyDate(timestamp))
                .expiration(toLegacyDate(expirationDate))
                .signWith(secretKeyService.getSecretKey())
                .compact();
    }

    private Date toLegacyDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.UTC).toInstant());
    }
}
