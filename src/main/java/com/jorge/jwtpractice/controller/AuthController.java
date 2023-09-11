package com.jorge.jwtpractice.controller;

import com.jorge.jwtpractice.model.dto.AuthResponse;
import com.jorge.jwtpractice.model.dto.LoginRequest;
import com.jorge.jwtpractice.model.dto.RegisterRequest;
import com.jorge.jwtpractice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request){
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(RegisterRequest request){
        return ResponseEntity.ok().body(authService.register(request));
    }
}
