package com.lead.security.service.token.parser;

import com.lead.security.model.AuthTokenDetails;
import com.lead.security.model.AuthTokenType;
import com.lead.security.service.token.secret.SecretKeyService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Date;

@Service
@AllArgsConstructor
class JwtTokenParser implements TokenParser {

    private static final String CLAIM_KEY_TOKEN_TYPE = "token_type";

    private SecretKeyService secretKeyService;
    private Clock clock;

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
}
