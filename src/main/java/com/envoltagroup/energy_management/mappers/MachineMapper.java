package com.envoltagroup.energy_management.mappers;

import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import com.envoltagroup.energy_management.dtos.machine.CreateMachineDTO;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.entities.Machine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MachineMapper {
    
    public static Machine fromCreateMachineDTOToMachine(CreateMachineDTO createMachineDTO) {
        return Machine.builder()
                .title(createMachineDTO.getTitle())
                .maximumEnergyInput(createMachineDTO.getMaximumEnergyInput())
                .minimumEnergyInput(createMachineDTO.getMinimumEnergyInput())
                .build();
    }

    public static MachineInformationDTO fromMachineToMachineInformationWithConsumptionsDTO(Machine machine) {
        List<EnergyConsumptionInformationDTO> energyConsumptionInformationDTOS = machine.getEnergyConsumptions().stream()
                .map(EnergyConsumptionMapper::fromEnergyConsumptionToEnergyConsumptionInformationDTO)
                .collect(Collectors.toList());
        
        return MachineMapper.buildMachineInformationDTOFromMachine(machine)
                .energyConsumptions(energyConsumptionInformationDTOS)
                .build();
    }

    public static MachineInformationDTO fromMachineToMachineInformationDTO(Machine machine) {
        return MachineMapper.buildMachineInformationDTOFromMachine(machine)
                .build();
    }
    
    private static MachineInformationDTO.MachineInformationDTOBuilder buildMachineInformationDTOFromMachine(Machine machine) {
        return MachineInformationDTO.builder()
                .id(machine.getId())
                .title(machine.getTitle())
                .minimumEnergyInput(machine.getMinimumEnergyInput())
                .maximumEnergyInput(machine.getMaximumEnergyInput());
    }
}
