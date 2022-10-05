package com.envoltagroup.energy_management.configurations.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockedAdminUser {
    
    public static final UserDetails ADMIN_USER = User.withUsername("admin")
            .password("123456")
            .passwordEncoder(new BCryptPasswordEncoder()::encode)
            .roles("ADMIN")
            .build();
    
}
