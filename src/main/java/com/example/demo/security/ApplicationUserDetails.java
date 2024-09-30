package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.ApplicationUser;

public class ApplicationUserDetails implements UserDetails {

    private ApplicationUser applicationUser;

    public ApplicationUserDetails(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return this.applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.applicationUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement logic as needed
    }
}
