package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) 
                        throws ServletException, IOException {
        
        String tokenString = request.getHeader("Authorization");
        
        if (tokenString != null && tokenString.startsWith("Bearer ")) {
            String token = tokenString.substring(7);
            if (!this.jwtHelper.validateToken(token)) {
                throw new AccessDeniedException("Token Expired");
            }

            String username = jwtHelper.getUsername(token);
            Authentication authentication = new ApplicationAuthentication(
                    (ApplicationUserDetails) applicationUserDetailsService.loadUserByUsername(username));
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
