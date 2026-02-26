package com.bank.management.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtDecoder jwtDecoder;
    private final BlacklistTokenService blacklistTokenService;

    public Map<String, Object> validateToken(String token) {
        try {
            if (blacklistTokenService.isTokenBlacklisted(token)) {
                throw new JwtException("Token is blacklisted");
            }

            Jwt jwt = jwtDecoder.decode(token);
            
            return Map.of(
                "valid", true,
                "user_guid", jwt.getClaim("user_guid"),
                "role", jwt.getClaim("role"),
                "expires_at", jwt.getExpiresAt(),
                "issued_at", jwt.getIssuedAt()
            );
        } catch (JwtException e) {
            return Map.of(
                "valid", false,
                "error", e.getMessage()
            );
        }
    }

    public boolean isTokenExpired(Jwt jwt) {
        return jwt.getExpiresAt() != null && jwt.getExpiresAt().isBefore(Instant.now());
    }

    public String extractUserGuid(Jwt jwt) {
        return jwt.getClaim("user_guid");
    }

    public String extractRole(Jwt jwt) {
        return jwt.getClaim("role");
    }
}