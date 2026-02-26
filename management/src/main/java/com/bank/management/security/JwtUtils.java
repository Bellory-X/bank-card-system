package com.bank.management.security;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    public UUID getCurrentUserGuid() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new IllegalStateException("No authenticated user found");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userGuid = jwt.getClaim("user_guid");
        
        if (userGuid == null) {
            throw new IllegalStateException("user_guid not found in token");
        }
        
        return UUID.fromString(userGuid);
    }

    public String getCurrentUserRole() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new IllegalStateException("No authenticated user found");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String role = jwt.getClaim("role");
        
        if (role == null) {
            throw new IllegalStateException("role not found in token");
        }
        
        return role;
    }

    public Jwt getCurrentJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new IllegalStateException("No authenticated user found");
        }

        return (Jwt) authentication.getPrincipal();
    }

    public boolean isAdmin() {
        return "ADMIN".equals(getCurrentUserRole());
    }

    public boolean hasAnyRole(String... roles) {
        String currentRole = getCurrentUserRole();
        for (String role : roles) {
            if (currentRole.equals(role)) {
                return true;
            }
        }
        return false;
    }
}