package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.repository.ApplicationUserRepository;
import com.example.demo.security.ApplicationUserDetailsService;
import com.example.demo.security.JwtHelper;

@Service
public class ApplicationService {

    private final ApplicationUserDetailsService applicationUserDetailsService;
    private final JwtHelper jwtHelper;
    private final ApplicationUserRepository applicationUserRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ApplicationService(
          ApplicationUserDetailsService applicationUserDetailsService,
          JwtHelper jwtHelper,
          ApplicationUserRepository applicationUserRepository,
          AuthenticationManager authenticationManager) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.jwtHelper = jwtHelper;
        this.applicationUserRepository = applicationUserRepository;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> loginService(ApplicationUser applicationUser) {
        // Validate input
        if (applicationUser == null || applicationUser.getUsername() == null || applicationUser.getUsername().isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create an authentication token
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword());

        try {
            // Authenticate the user
            Authentication authentication = this.authenticationManager.authenticate(authToken);
            
            // Generate JWT token upon successful authentication
            String token = jwtHelper.generateToken(applicationUser.getUsername());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            // Handle authentication failure
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }
}