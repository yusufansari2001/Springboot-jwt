package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.service.ApplicationService;

@RestController  // Optional: Base URL for your API
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ApplicationUser applicationUser) {
        return this.applicationService.loginService(applicationUser);
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
