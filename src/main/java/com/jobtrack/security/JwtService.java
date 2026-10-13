package com.jobtrack.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {
    @Value ("${jwt.secret}")
    private String secret;

    public String generateToken(String email){
        SecretKey key= Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+1000*60*60))
            .signWith(key)
            .compact();
        }

    public String extractEmail(String token){
        SecretKey key=Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }
}
