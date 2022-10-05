package com.envoltagroup.energy_management.data_builders;

import com.envoltagroup.energy_management.dtos.machine.CreateMachineDTO;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.dtos.machine.UpdateMachineDTO;
import com.envoltagroup.energy_management.entities.Machine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MachineDataBuilder {

    public static final CreateMachineDTO CREATE_MACHINE_DTO = CreateMachineDTO.builder()
            .title("Turning Machine")
            .build();
    
    public static final Machine MACHINE = Machine.builder()
            .id(1L)
            .title("Turning Machine")
            .energyConsumptions(Arrays.asList(EnergyConsumptionDataBuilder.ENERGY_CONSUMPTION))
            .minimumEnergyInput(BigDecimal.ONE)
            .maximumEnergyInput(BigDecimal.TEN)
            .build();
    
    public static final UpdateMachineDTO UPDATE_MACHINE_DTO = UpdateMachineDTO.builder()
            .title("Other Machine")
            .build();
    
    public static final MachineInformationDTO MACHINE_INFORMATION_DTO = MachineInformationDTO.builder()
            .id(1L)
            .title("Bulldozer")
            .energyConsumptions(Arrays.asList(EnergyConsumptionDataBuilder.ENERGY_CONSUMPTION_INFORMATION_DTO))
            .build();
            
}
