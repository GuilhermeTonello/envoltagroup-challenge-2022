package com.envoltagroup.energy_management.mappers;

import com.envoltagroup.energy_management.data_builders.EnergyConsumptionDataBuilder;
import com.envoltagroup.energy_management.dtos.energy_consumption.CreateEnergyConsumptionDTO;
import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import com.envoltagroup.energy_management.entities.EnergyConsumption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConsumptionMapperTest {

    @Test
    void should_mapFrom_CreateEnergyConsumptionDTO_to_EnergyConsumption() {
        CreateEnergyConsumptionDTO createEnergyConsumptionDTO = EnergyConsumptionDataBuilder.CREATE_ENERGY_CONSUMPTION_DTO;
        EnergyConsumption energyConsumption = EnergyConsumptionMapper.fromCreateEnergyConsumptionDTOToEnergyConsumption(createEnergyConsumptionDTO);
        
        assertEquals(createEnergyConsumptionDTO.getConsumption(), energyConsumption.getConsumption());
        assertEquals(createEnergyConsumptionDTO.getCreatedAt(), energyConsumption.getCreatedAt());
    }

    @Test
    void should_mapFrom_EnergyConsumption_to_EnergyConsumptionInformationDTO() {
        EnergyConsumption energyConsumption = EnergyConsumptionDataBuilder.ENERGY_CONSUMPTION;
        EnergyConsumptionInformationDTO energyConsumptionInformationDTO = EnergyConsumptionMapper.fromEnergyConsumptionToEnergyConsumptionInformationDTO(energyConsumption);

        assertEquals(energyConsumption.getId(), energyConsumptionInformationDTO.getId());
        assertEquals(energyConsumption.getConsumption(), energyConsumptionInformationDTO.getConsumption());
        assertEquals(energyConsumption.getCreatedAt(), energyConsumptionInformationDTO.getCreatedAt());
    }
    
}