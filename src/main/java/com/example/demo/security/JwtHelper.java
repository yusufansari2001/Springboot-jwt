package com.example.demo.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtHelper {

    @Value("${jwt.expiry}")
    long expiry;

    @Value("${jwt.secret}")
    String secret;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .sign(Algorithm.HMAC512(secret));
    }

    public boolean validateToken(String jwtToken) {
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        return decodedJWT.getExpiresAt().after(new Date(System.currentTimeMillis()));
    }

    public String getUsername(String jwtToken) {
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        return decodedJWT.getSubject();
    }
}
