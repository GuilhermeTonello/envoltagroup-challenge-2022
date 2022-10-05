package com.envoltagroup.energy_management.configurations.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtUtils {
    
    private final JwtConfiguration jwtConfiguration;
    
    public String generateToken(Authentication authentication) {
        Date actualDate = new Date();
        Date expirationDate = new Date(actualDate.getTime() + jwtConfiguration.getExpirationTime());
        
        return JWT.create()
                .withIssuer(jwtConfiguration.getIssuer())
                .withExpiresAt(expirationDate)
                .withSubject(authentication.getName())
                .sign(jwtConfiguration.getSecret());
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) return false;
        try {
            decodedJwt(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getSubject(String token) {
        return decodedJwt(token).getSubject();
    }
    
    private DecodedJWT decodedJwt(String token) {
        return JWT.require(jwtConfiguration.getSecret())
                .withIssuer(jwtConfiguration.getIssuer())
                .build()
                .verify(token);
    }
    
}
