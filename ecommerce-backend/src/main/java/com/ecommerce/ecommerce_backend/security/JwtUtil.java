package com.ecommerce.ecommerce_backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24h

    // tạo token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // lấy username từ token
    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    // validate token
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public String getTokenFromAuthHeader(String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return null;
        }

        // Remove whitespace and check if it starts with "Bearer"
        String trimmedHeader = authHeader.trim();

        if (trimmedHeader.toLowerCase().startsWith("bearer ")) {
            // Extract the token part after "Bearer "
            return trimmedHeader.substring(7).trim();
        }

        return null;
    }
}
