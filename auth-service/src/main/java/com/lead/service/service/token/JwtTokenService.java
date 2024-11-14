package com.lead.service.service.token;

import com.lead.common.security.service.secret.SecretKeyService;
import com.lead.service.model.dto.AuthTokenDetails;
import com.lead.service.model.dto.AuthTokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtTokenService implements TokenService {

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

    @Override
    public AuthTokenDetails parse(String token) {
        var claims = getClaims(token);
        var userName = claims.getSubject();
        var isTokenExpired = isTokenExpired(claims);
        var tokenType = AuthTokenType.valueOf(claims.get(CLAIM_KEY_TOKEN_TYPE).toString());
        return new AuthTokenDetails(userName, isTokenExpired, tokenType);
    }

    private boolean isTokenExpired(Claims claims) {
        var currentTimestamp = new Date(clock.millis());
        var tokenExpiration = claims.getExpiration();
        return tokenExpiration.before(currentTimestamp);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKeyService.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
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
