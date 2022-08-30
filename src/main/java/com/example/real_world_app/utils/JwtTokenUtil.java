package com.example.real_world_app.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.token.TokenPayload;

import io.jsonwebtoken.Claims;
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

    public TokenPayload getTokenPayload(String token) {
        return getClaimsFromToken(token,(Claims claims) ->{
            Map<String, Object> mapResult = (Map<String, Object>) claims.get("payload");
            return TokenPayload.builder()
            .uid((Integer) mapResult.get("uid"))
            .email((String) mapResult.get("email"))
            .build();
        });
    }

    private<T> T getClaimsFromToken(String token, Function<Claims,T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }

    public boolean validateToken(String token, User user) {
        TokenPayload tokenPayload = getTokenPayload(token);
        return tokenPayload.getUid() == user.getId() 
        && tokenPayload.getEmail().equals(user.getEmail())
        && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getClaimsFromToken(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
}
