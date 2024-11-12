package com.lead.service.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Clock;
import java.util.Date;

@Service
public class JwtTokenService implements TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshExpiration;

    private final Clock clock;

    public JwtTokenService(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String generateAccessToken(String username) {
        return generateToken(username, accessExpiration);
    }

    @Override
    public String generateRefreshToken(String username) {
        return generateToken(username, refreshExpiration);
    }

    private String generateToken(String username, Long expiration) {
        long timestamp = clock.millis();
        Date currentTimestamp = new Date(timestamp);
        Date expirationDate = new Date(timestamp + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(currentTimestamp)
                .expiration(expirationDate)
                .signWith(createSecretKey())
                .compact();
    }

    private Key createSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
