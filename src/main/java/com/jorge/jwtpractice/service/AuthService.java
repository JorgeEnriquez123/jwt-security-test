package com.jorge.jwtpractice.service;

import com.jorge.jwtpractice.jwt.JwtService;
import com.jorge.jwtpractice.model.Role;
import com.jorge.jwtpractice.model.User;
import com.jorge.jwtpractice.model.dto.AuthResponse;
import com.jorge.jwtpractice.model.dto.LoginRequest;
import com.jorge.jwtpractice.model.dto.RegisterRequest;
import com.jorge.jwtpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final AuthenticationManager auth;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request){
        UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        auth.authenticate(authtoken); //Throws exception if not authenticated
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
