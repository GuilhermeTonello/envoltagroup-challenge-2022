package com.envoltagroup.energy_management.mappers;

import com.envoltagroup.energy_management.data_builders.MachineDataBuilder;
import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import com.envoltagroup.energy_management.dtos.machine.CreateMachineDTO;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.entities.EnergyConsumption;
import com.envoltagroup.energy_management.entities.Machine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MachineMapperTest {

    @Test
    void should_mapFrom_CreateMachineDTO_to_Machine() {
        CreateMachineDTO createMachineDTO = MachineDataBuilder.CREATE_MACHINE_DTO;
        Machine machine = MachineMapper.fromCreateMachineDTOToMachine(createMachineDTO);

        assertEquals(createMachineDTO.getTitle(), machine.getTitle());
    }

    @Test
    void should_mapFrom_Machine_to_MachineInformationDTO_with_EnergyConsumptions() {
        Machine machine = MachineDataBuilder.MACHINE;
        MachineInformationDTO machineInformationDTO = MachineMapper.fromMachineToMachineInformationWithConsumptionsDTO(machine);

        EnergyConsumption machineEnergyConsumption = machine.getEnergyConsumptions().stream().findFirst().get();
        EnergyConsumptionInformationDTO machineInformationDTOEnergyConsumption =machineInformationDTO.getEnergyConsumptions().stream().findFirst().get();
        
        assertEquals(machine.getId(), machineInformationDTO.getId());
        assertEquals(machine.getTitle(), machineInformationDTO.getTitle());
        
        assertEquals(machineEnergyConsumption.getId(), machineInformationDTOEnergyConsumption.getId());
        assertEquals(machineEnergyConsumption.getCreatedAt(), machineInformationDTOEnergyConsumption.getCreatedAt());
        assertEquals(machineEnergyConsumption.getConsumption(), machineInformationDTOEnergyConsumption.getConsumption());
        assertFalse(machineInformationDTO.getEnergyConsumptions().isEmpty());
    }

    @Test
    void should_mapFrom_Machine_to_MachineInformationDTO() {
        Machine machine = MachineDataBuilder.MACHINE;
        MachineInformationDTO machineInformationDTO = MachineMapper.fromMachineToMachineInformationDTO(machine);
        
        assertEquals(machine.getId(), machineInformationDTO.getId());
        assertEquals(machine.getTitle(), machineInformationDTO.getTitle());
        
        assertTrue(machineInformationDTO.getEnergyConsumptions().isEmpty());
    }

}