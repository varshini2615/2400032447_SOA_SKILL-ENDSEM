package com.example.jwtgeneration.dto;

import jakarta.validation.constraints.NotBlank;

public record JwtRequest(
        @NotBlank(message = "username is required") String username,
        @NotBlank(message = "role is required") String role
) {
}
