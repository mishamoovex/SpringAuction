package com.lead.service.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.function.Function;

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

    @Override
    public boolean isTokenExpired(String token) {
        var currentTimestamp = new Date(clock.millis());
        var tokenExpiration = extractExpiration(token);
        return tokenExpiration.before(currentTimestamp);
    }

    @Override
    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = Jwts.parser()
                .setSigningKey(createSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    private String generateToken(String username, Long expiration) {
        var timestamp = LocalDateTime.now(clock);
        var expirationDate = timestamp.plusSeconds(expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(toLegacyDate(timestamp))
                .expiration(toLegacyDate(expirationDate))
                .signWith(createSecretKey())
                .compact();
    }

    private Date toLegacyDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.UTC).toInstant());
    }

    private Key createSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
