package com.envoltagroup.energy_management.dtos.energy_consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEnergyConsumptionDTO {
    
    @NotNull
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull
    @JsonProperty("consumption")
    private BigDecimal consumption;

}
