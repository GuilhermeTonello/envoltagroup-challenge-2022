package com.envoltagroup.energy_management.configurations.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private Long expirationTime;
    
    private static final String TOKEN_PREFIX = "Bearer ";
    
    @Value("${jwt.issuer}")
    private String issuer;

    public Algorithm getSecret() {
        return Algorithm.HMAC256(secret);
    }
    
    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public String getIssuer() {
        return issuer;
    }

    public Long getExpirationTime() {
        return expirationTime * 100;
    }
    
}
