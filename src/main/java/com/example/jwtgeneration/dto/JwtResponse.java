package com.example.jwtgeneration.dto;

public record JwtResponse(
        String tokenType,
        String token,
        String username,
        String role
) {
}
