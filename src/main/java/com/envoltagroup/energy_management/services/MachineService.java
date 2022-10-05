package com.envoltagroup.energy_management.services;

import com.envoltagroup.energy_management.dtos.energy_consumption.CreateEnergyConsumptionDTO;
import com.envoltagroup.energy_management.dtos.machine.CreateMachineDTO;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.dtos.machine.UpdateMachineDTO;
import com.envoltagroup.energy_management.entities.EnergyConsumption;
import com.envoltagroup.energy_management.entities.Machine;
import com.envoltagroup.energy_management.exceptions.MachineNotFoundException;
import com.envoltagroup.energy_management.mappers.EnergyConsumptionMapper;
import com.envoltagroup.energy_management.mappers.MachineMapper;
import com.envoltagroup.energy_management.repositories.EnergyConsumptionRepository;
import com.envoltagroup.energy_management.repositories.MachineRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MachineService {
    
    private final MachineRepository machineRepository;
    private final EnergyConsumptionRepository energyConsumptionRepository;
    private final EmailService emailService;
    
    public MachineInformationDTO createMachine(CreateMachineDTO createMachineDTO) {
        Machine machine = MachineMapper.fromCreateMachineDTOToMachine(createMachineDTO);
        Machine createdMachine = machineRepository.save(machine);
        return MachineMapper.fromMachineToMachineInformationDTO(createdMachine);
    }

    public MachineInformationDTO getMachineById(Long machineId) {
        Machine machine = this.findMachineById(machineId);
        return MachineMapper.fromMachineToMachineInformationWithConsumptionsDTO(machine);
    }
    
    public Page<MachineInformationDTO> listMachines(Pageable pageable, boolean expandConsumptions) {
        Page<Machine> machines = machineRepository.findAll(pageable);
        return expandConsumptions 
                ? machines.map(MachineMapper::fromMachineToMachineInformationWithConsumptionsDTO)
                : machines.map(MachineMapper::fromMachineToMachineInformationDTO);
    }

    public MachineInformationDTO updateMachine(Long machineId, UpdateMachineDTO updateMachineDTO) {
        Machine machineToUpdate = this.findMachineById(machineId);
        machineToUpdate.setTitle(updateMachineDTO.getTitle());
        machineRepository.save(machineToUpdate);
        return MachineMapper.fromMachineToMachineInformationWithConsumptionsDTO(machineToUpdate);
    }
    
    public void deleteMachineById(Long machineId) {
        this.findMachineById(machineId);
        machineRepository.deleteById(machineId);
    }

    public MachineInformationDTO addEnergyConsumption(Long machineId, CreateEnergyConsumptionDTO createEnergyConsumptionDTO) {
        Machine machineOnDatabase = this.findMachineById(machineId);
        
        EnergyConsumption energyConsumption = EnergyConsumptionMapper.fromCreateEnergyConsumptionDTOToEnergyConsumption(createEnergyConsumptionDTO);
        energyConsumption.setMachine(machineOnDatabase);
        energyConsumptionRepository.save(energyConsumption);
        
        this.verifyEnergyInput(machineOnDatabase, createEnergyConsumptionDTO.getConsumption());
        
        return MachineMapper.fromMachineToMachineInformationWithConsumptionsDTO(machineOnDatabase);
    }
    
    private Machine findMachineById(Long machineId) {
        Optional<Machine> machine = machineRepository.findById(machineId);
        return machine.orElseThrow(() -> new MachineNotFoundException(machineId));
    }
    
    private void verifyEnergyInput(Machine machine, BigDecimal energyInput) {
        if (energyInput.compareTo(machine.getMaximumEnergyInput()) > 0) {
            emailService.sendEmail("Consumo alto de energia - " + machine.getTitle(), 
                    "O consumo de energia da máquina " + machine.getTitle() +" foi de " + energyInput + ", porém o máximo estipulado era de " + machine.getMaximumEnergyInput(),
                    "suporte@chaleutilidades.com", "tonello9401@gmail.com");
        } else if (energyInput.compareTo(machine.getMinimumEnergyInput()) < 0) {
            emailService.sendEmail("Consumo baixo de energia - " + machine.getTitle(),
                    "O consumo de energia da máquina " + machine.getTitle() +" foi de " + energyInput + ", porém o mínimo estipulado era de " + machine.getMinimumEnergyInput(),
                    "suporte@chaleutilidades.com", "tonello9401@gmail.com");
        }
    }
}
