package com.envoltagroup.energy_management.controllers;

import com.envoltagroup.energy_management.dtos.security.LoginRequestDTO;
import com.envoltagroup.energy_management.dtos.security.SuccessfulLoginDTO;
import com.envoltagroup.energy_management.configurations.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.envoltagroup.energy_management.utils.ConstantUtils.AUTH_CONTROLLER;

@RestController
@RequestMapping(AUTH_CONTROLLER)
@AllArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public SuccessfulLoginDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }
    
}
