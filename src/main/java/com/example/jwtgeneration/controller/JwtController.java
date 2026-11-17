package com.example.jwtgeneration.controller;

import com.example.jwtgeneration.dto.JwtRequest;
import com.example.jwtgeneration.dto.JwtResponse;
import com.example.jwtgeneration.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
@Tag(name = "JWT", description = "JWT token generation APIs")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/generate")
    @Operation(summary = "Generate a signed JWT using username and role")
    public ResponseEntity<JwtResponse> generateToken(
            @Valid @RequestBody JwtRequest request) {

        String token = jwtService.generateToken(
                request.username(), request.role());

        return ResponseEntity.ok(new JwtResponse(
                "Bearer", token, request.username(), request.role()));
    }
}
