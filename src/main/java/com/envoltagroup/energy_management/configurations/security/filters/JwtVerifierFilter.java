package com.envoltagroup.energy_management.configurations.security.filters;

import com.envoltagroup.energy_management.configurations.security.MockedAdminUser;
import com.envoltagroup.energy_management.configurations.security.jwt.JwtConfiguration;
import com.envoltagroup.energy_management.configurations.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtVerifierFilter extends OncePerRequestFilter {
    
    private final JwtConfiguration jwtConfiguration;
    private final JwtUtils jwtUtils;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String tokenPrefix = jwtConfiguration.getTokenPrefix();
        boolean isHeaderValid = authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith(tokenPrefix);
        if (isHeaderValid) {
            String token = authorizationHeader.replace(tokenPrefix, "");
            if (jwtUtils.validateToken(token)) {
                UserDetails adminUser = MockedAdminUser.ADMIN_USER;
                UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(adminUser, null, adminUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePassword);
            }
        }
        filterChain.doFilter(request, response);
    }
}
