package com.proton.services.municipe;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenService {
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 600000; //10 min

    public String generateToken(String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(secretKey)
        .compact();
    }

    public String validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return "valid";
        } catch (JwtException e) {
            // Token é inválido (falhou na verificação)
            return "invalid token";
        }
    }

   
    public String getEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            // Logue o erro ou faça o tratamento apropriado aqui
            e.printStackTrace();
            return null;
        }
    }
}
    



    
    

