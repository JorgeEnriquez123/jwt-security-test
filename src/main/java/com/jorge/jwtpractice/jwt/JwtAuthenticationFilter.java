package com.jorge.jwtpractice.jwt;

import com.jorge.jwtpractice.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);                  // * GET TOKEN

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        final String userEmail = jwtService.getUsernameFromToken(token);    // ? FIND USERNAME with TOKEN


        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){ // * SecurityContext will always be null
            UserDetails userAuthenticated = userDetailsService.loadUserByUsername(userEmail);    // ! Trigger Auth method | It was configured on ApplicationConfig
            if(jwtService.isTokenValid(token, userAuthenticated)){      // If Token belongs to User and it's not expired
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                        userEmail,
                        null,
                        userAuthenticated.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Setting Request info
                SecurityContextHolder.getContext().setAuthentication(authtoken);                  // Setting SecurityContextHolder
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}
