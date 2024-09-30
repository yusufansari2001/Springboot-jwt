package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.repository.ApplicationUserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByUsername(username);

        if (applicationUser.isEmpty()) {
            throw new UsernameNotFoundException("User not Found");
        }

        return new ApplicationUserDetails(applicationUser.get());
    }
}