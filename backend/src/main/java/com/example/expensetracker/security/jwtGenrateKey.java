package com.example.expensetracker.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

public class jwtGenrateKey {
    public static void main(String[] args) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String encoded = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encoded);
    }
}
