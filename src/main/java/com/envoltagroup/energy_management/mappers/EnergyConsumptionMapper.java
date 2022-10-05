package com.envoltagroup.energy_management.mappers;

import com.envoltagroup.energy_management.dtos.energy_consumption.CreateEnergyConsumptionDTO;
import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import com.envoltagroup.energy_management.entities.EnergyConsumption;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnergyConsumptionMapper {
    
    public static EnergyConsumption fromCreateEnergyConsumptionDTOToEnergyConsumption(CreateEnergyConsumptionDTO createEnergyConsumptionDTO) {
        return EnergyConsumption.builder()
                .consumption(createEnergyConsumptionDTO.getConsumption())
                .createdAt(createEnergyConsumptionDTO.getCreatedAt())
                .build();
    }
    
    public static EnergyConsumptionInformationDTO fromEnergyConsumptionToEnergyConsumptionInformationDTO(EnergyConsumption energyConsumption) {
        return EnergyConsumptionInformationDTO.builder()
                .id(energyConsumption.getId())
                .consumption(energyConsumption.getConsumption())
                .createdAt(energyConsumption.getCreatedAt())
                .build();
    }
    
}
