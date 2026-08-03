package com.example.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;

@Component
public class SecurityExceptionFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            String message = "Authentication failed: " + ex.getMessage();
            if (ex.getClass().getName().contains("ExpiredJwtException")) {
                message = "Token expired";
            } else if (ex.getClass().getName().contains("SignatureException") || 
                       ex.getClass().getName().contains("MalformedJwtException")) {
                message = "JWT invalid";
            }
            request.setAttribute("security-exception-message", message);
            authenticationEntryPoint.commence(request, response, new BadCredentialsException(message, ex));
        }
    }
}
