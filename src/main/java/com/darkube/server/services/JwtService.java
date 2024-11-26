package com.darkube.server.services;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.darkube.server.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretKey = "";

    public JwtService() {
        this.secretKey = System.getProperty("jwt");
    }

    public String generateToken(User user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException("`user` can't be a null value");
        }
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("role", user.getUserRole());
        return Jwts.builder()
                .subject(user.getUsername())
                .signWith(getKey())
                .claims()
                .add(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean verify(String token) {
        try {
            return extractUsername(token) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(getKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public String extractEmail(String token) {
        Jws<Claims> user = Jwts.parser()
                .verifyWith(getKey()).build()
                .parseSignedClaims(token);
        Object email = user.getPayload().get("email");
        return (String) email;
    }

    public HashMap<String, Object> extractClaims(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .verifyWith(getKey()).build()
                .parseSignedClaims(token);
        HashMap<String, Object> map = new HashMap<>();
        Claims claims = jwsClaims.getPayload();
        claims.forEach((key, value) -> {
            map.put(key, value);
        });
        return map;
    }

}
