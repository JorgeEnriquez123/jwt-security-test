package com.jorge.jwtpractice.config;

import com.jorge.jwtpractice.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.jorge.jwtpractice.model.Permission.*;
import static com.jorge.jwtpractice.model.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( authRequest -> authRequest
                        .requestMatchers(GET, "manager/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(POST, "manager/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                        .requestMatchers(PUT, "manager/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                        .requestMatchers(DELETE, "manager/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                        .requestMatchers("/manager/**").hasAnyRole(ADMIN.name(), MANAGER.name())

                        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/admin/**").hasRole(ADMIN.name())

                        .requestMatchers(GET, "/basic/specialmessage").hasAuthority(USER_READ_SPECIAL.name())
                        .requestMatchers("/basic/publicmessage").permitAll()
                        .requestMatchers("/basic/**").authenticated()
                )
                .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
