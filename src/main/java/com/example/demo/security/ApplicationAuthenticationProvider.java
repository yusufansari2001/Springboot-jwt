package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
//import org.springframework.security.core.AuthenticationException;


@Component
public class ApplicationAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService applicationUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = applicationUserDetailsService
                .loadUserByUsername(authentication.getPrincipal().toString());

        if (!userDetails.getPassword().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Username/password Incorrect");
        }

        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApplicationAuthentication.class.isAssignableFrom(authentication);
    }
}
