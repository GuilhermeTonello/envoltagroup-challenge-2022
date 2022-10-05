package com.envoltagroup.energy_management.dtos.energy_consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEnergyConsumptionDTO {
    
    @NotNull
    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @NotNull
    @JsonProperty("consumption")
    private BigDecimal consumption;

}
