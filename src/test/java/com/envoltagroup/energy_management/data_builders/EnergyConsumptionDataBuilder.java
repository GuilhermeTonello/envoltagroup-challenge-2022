package com.envoltagroup.energy_management.data_builders;

import com.envoltagroup.energy_management.dtos.energy_consumption.CreateEnergyConsumptionDTO;
import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import com.envoltagroup.energy_management.entities.EnergyConsumption;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnergyConsumptionDataBuilder {

    public static final EnergyConsumption ENERGY_CONSUMPTION = EnergyConsumption.builder()
            .id(1L)
            .createdAt(OffsetDateTime.now())
            .consumption(new BigDecimal("320.75"))
            .build();
    
    public static final CreateEnergyConsumptionDTO CREATE_ENERGY_CONSUMPTION_DTO = CreateEnergyConsumptionDTO.builder()
            .createdAt(OffsetDateTime.now())
            .consumption(new BigDecimal("320.75"))
            .build();

    public static final EnergyConsumptionInformationDTO ENERGY_CONSUMPTION_INFORMATION_DTO = EnergyConsumptionInformationDTO.builder()
            .id(1L)
            .createdAt(OffsetDateTime.now())
            .consumption(new BigDecimal("320.75"))
            .build();
    
}
