package com.example.expensetracker.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class JwtService {
    
    private final SecretKey secretKey;

    public String generateToken(String username){

        Date createdAt = new Date();

        Date expiredAt = new Date(createdAt.getTime() + 15 * 60 * 1000);

        return Jwts.builder()
            .subject(username)
            .issuedAt(createdAt)
            .expiration(expiredAt)
            .signWith(secretKey)
            .compact();
    }

    public String extractUserName(String token){
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
}
