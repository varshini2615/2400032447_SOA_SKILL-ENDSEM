package com.example.jwtgeneration.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    @Bean
    public SecretKey jwtSigningKey(
            @Value("${app.jwt.secret}") String base64Secret) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
