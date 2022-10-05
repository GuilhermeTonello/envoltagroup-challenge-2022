package com.envoltagroup.energy_management.dtos.energy_consumption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnergyConsumptionInformationDTO {
    
    private Long id;
    private OffsetDateTime createdAt;
    private BigDecimal consumption;

}
