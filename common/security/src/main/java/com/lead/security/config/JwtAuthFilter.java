package com.lead.security.config;

import com.lead.security.model.AuthTokenType;
import com.lead.security.service.token.parser.TokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private UserDetailsService userDetailsService;
    private TokenParser tokenParser;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            var authHeader = request.getHeader(AUTHORIZATION_HEADER);
            var accessToken = authHeader.substring(BEARER_PREFIX.length());
            var tokenDetails = tokenParser.parse(accessToken);
            var isTokenValid = tokenDetails.getAuthTokenType() == AuthTokenType.ACCESS
                    && tokenDetails.getUsername() != null
                    && !tokenDetails.getIsExpired();

            if (isTokenValid) {
                var userDetails = userDetailsService.loadUserByUsername(tokenDetails.getUsername());
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        authHeader,
                        userDetails.getAuthorities()
                );
                var authDetails = new WebAuthenticationDetailsSource().buildDetails(request);

                authToken.setDetails(authDetails);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (NullPointerException e) {
            //no-op
        }

        filterChain.doFilter(request, response);
    }
}
