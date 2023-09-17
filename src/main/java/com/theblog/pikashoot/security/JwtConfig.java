package com.theblog.pikashoot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Component
public class JwtConfig {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateJwtToken(Authentication authentication){

        Date createdAt = new Date();
        Date expireAt = new Date(createdAt.getTime() + 70000);

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .setIssuedAt(createdAt)
                .setExpiration(expireAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token ){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException("Token expired or not found" + exception);
        }
    }
}
