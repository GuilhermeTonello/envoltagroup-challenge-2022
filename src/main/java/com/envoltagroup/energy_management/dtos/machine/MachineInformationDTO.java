package com.envoltagroup.energy_management.dtos.machine;

import com.envoltagroup.energy_management.dtos.energy_consumption.EnergyConsumptionInformationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MachineInformationDTO {
    
    private Long id;
    
    private String title;
    
    @Builder.Default
    private List<EnergyConsumptionInformationDTO> energyConsumptions = new ArrayList<>();
    
}
