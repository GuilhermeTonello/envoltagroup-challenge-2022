package com.envoltagroup.energy_management.dtos.machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMachineDTO {
    
    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("title")
    private String title;
    
    @NotNull
    @Min(0)
    @JsonProperty("minimumEnergyInput")
    private BigDecimal minimumEnergyInput;

    @NotNull
    @JsonProperty("maximumEnergyInput")
    private BigDecimal maximumEnergyInput;
    
}
