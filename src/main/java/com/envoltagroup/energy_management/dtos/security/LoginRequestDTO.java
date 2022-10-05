package com.envoltagroup.energy_management.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
    
    @NotBlank
    @Size(min = 1, max = 255)
    private String username;
    
    @NotBlank
    @Size(min = 1, max = 255)
    private String password;
    
}
