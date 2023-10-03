package com.jorge.jwtpractice.service;

import com.jorge.jwtpractice.jwt.JwtService;
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

import static com.jorge.jwtpractice.model.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final AuthenticationManager auth;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request){
        try {
            UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            auth.authenticate(authtoken); //Throws exception if not authenticated
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        }catch (Exception e){
            return AuthResponse.builder().token("Invalid credentials").build();
        }
    }

    public AuthResponse register(RegisterRequest request){
        var roleFromRequest = request.getRole();
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleFromRequest == null ? USER : roleFromRequest)
                .build();
        var usersaved = repository.save(user);
        System.out.println(usersaved.getAuthorities()); // Just extra info
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
