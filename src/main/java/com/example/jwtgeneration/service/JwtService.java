package com.example.jwtgeneration.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationMillis;

    public JwtService(
            SecretKey signingKey,
            @Value("${app.jwt.expiration-ms:3600000}") long expirationMillis) {
        this.signingKey = signingKey;
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(String username, String role) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMillis);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(signingKey)
                .compact();
    }
}
