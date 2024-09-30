package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class ApplicationAuthentication implements Authentication {

    private final ApplicationUserDetails applicationUserDetails;
    private boolean authenticated = false;

    public ApplicationAuthentication(ApplicationUserDetails applicationUserDetails) {
        this.applicationUserDetails = applicationUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public Object getCredentials() {
        return applicationUserDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return applicationUserDetails;
    }

    @Override
    public Object getPrincipal() {
        return applicationUserDetails.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return applicationUserDetails.getUsername();
    }
}
