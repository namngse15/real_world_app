package com.example.real_world_app.utils;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.token.TokenPayload;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    
    private final String secret = "real_world_application";

    public String generateToken(User user, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        //add payload to token
        TokenPayload tokenPayload = TokenPayload.builder().uid(user.getId()).email(user.getEmail()).build();
        //set token into claim
        claims.put("payload", tokenPayload);
        //return token
        return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+ expiration*1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
        
    }
}
