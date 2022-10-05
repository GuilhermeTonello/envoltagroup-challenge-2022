package com.envoltagroup.energy_management.configurations.security;

import com.envoltagroup.energy_management.configurations.security.jwt.JwtUtils;
import com.envoltagroup.energy_management.dtos.security.LoginRequestDTO;
import com.envoltagroup.energy_management.dtos.security.SuccessfulLoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    public SuccessfulLoginDTO login(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        String token = jwtUtils.generateToken(authentication);
        return SuccessfulLoginDTO.builder()
                .token(token)
                .build();
    }
    
}
