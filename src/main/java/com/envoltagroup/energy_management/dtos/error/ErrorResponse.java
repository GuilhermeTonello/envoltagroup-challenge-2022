package com.envoltagroup.energy_management.dtos.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    
    private Integer statusCode;
    private String statusText;
    private List<FieldErrors> fieldErrors = new ArrayList<>();
    
}
