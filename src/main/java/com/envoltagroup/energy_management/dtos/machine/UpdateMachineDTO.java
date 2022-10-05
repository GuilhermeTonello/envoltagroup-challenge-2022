package com.envoltagroup.energy_management.dtos.machine;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdateMachineDTO {

    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("title")
    private String title;
    
}
