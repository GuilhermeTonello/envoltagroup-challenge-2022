package com.envoltagroup.energy_management.services;

import com.envoltagroup.energy_management.data_builders.DefaultPageableDataBuilder;
import com.envoltagroup.energy_management.data_builders.EnergyConsumptionDataBuilder;
import com.envoltagroup.energy_management.data_builders.MachineDataBuilder;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {
    
    @Mock
    private MachineRepository machineRepository;
    
    @Mock
    private EnergyConsumptionRepository energyConsumptionRepository;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private MachineService machineService;
    
    @Test
    void should_createMachine_andReturnIt() {
        CreateMachineDTO createMachineDTO = MachineDataBuilder.CREATE_MACHINE_DTO;
        Machine machine = MachineMapper.fromCreateMachineDTOToMachine(createMachineDTO);
        
        Machine machineToCreate = MachineDataBuilder.MACHINE;
        when(machineRepository.save(machine))
            .thenReturn(machineToCreate);
        
        MachineInformationDTO createdMachine = machineService.createMachine(createMachineDTO);
        
        assertNotNull(createdMachine);
        assertEquals(machineToCreate.getTitle(), createdMachine.getTitle());
        
        verify(machineRepository).save(machine);
    }

    @Test
    void should_findMachineById_andReturnIt() {
        Machine machine = MachineDataBuilder.MACHINE;
        Long machineId = machine.getId();
        
        when(machineRepository.findById(machineId))
                .thenReturn(Optional.of(machine));

        MachineInformationDTO findedMachine = machineService.getMachineById(machineId);

        assertNotNull(findedMachine);
        assertEquals(machine.getId(), findedMachine.getId());
        assertEquals(machine.getTitle(), findedMachine.getTitle());
        
        verify(machineRepository).findById(machineId);
    }

    @Test
    void when_findMachineById_should_throwException_when_machineNotFound() {
        Long machineId = MachineDataBuilder.MACHINE.getId();
        
        when(machineRepository.findById(machineId))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class, () -> machineService.getMachineById(machineId));

        verify(machineRepository).findById(machineId);
    }

    @Test
    void should_listMachines_withoutConsumptions() {
        MachineInformationDTO firstMachineInformationDTO = assertListMachinesAndReturnFirstMachineInformationDTO(false);
        assertTrue(firstMachineInformationDTO.getEnergyConsumptions().isEmpty());
    }

    @Test
    void should_listMachines_withConsumptions() {
        MachineInformationDTO firstMachineInformationDTO = assertListMachinesAndReturnFirstMachineInformationDTO(true);
        assertFalse(firstMachineInformationDTO.getEnergyConsumptions().isEmpty());
    }
    
    @Test
    void should_updateMachine() {
        Machine machineToUpdate = MachineDataBuilder.MACHINE;
        String machineTitleBeforeUpdate = machineToUpdate.getTitle();
        UpdateMachineDTO updateMachineDTO = MachineDataBuilder.UPDATE_MACHINE_DTO;
        Long machineId = 1L;

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.of(machineToUpdate));
        
        when(machineRepository.save(machineToUpdate))
                .thenReturn(any(Machine.class));
        
        MachineInformationDTO updatedMachine = machineService.updateMachine(machineId, updateMachineDTO);

        assertEquals(updateMachineDTO.getTitle(), updatedMachine.getTitle());
        assertNotEquals(machineTitleBeforeUpdate, updateMachineDTO.getTitle());
        
        verify(machineRepository).save(any(Machine.class));
    }

    @Test
    void when_updateMachine_should_throwException_when_machineNotFound() {
        UpdateMachineDTO updateMachineDTO = MachineDataBuilder.UPDATE_MACHINE_DTO;
        Long machineId = 1L;
        
        when(machineRepository.findById(machineId))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class, () -> machineService.updateMachine(machineId, updateMachineDTO));

        verify(machineRepository, never()).save(any(Machine.class));
    }
    
    @Test
    void should_deleteMachineById() {
        Machine machine = MachineDataBuilder.MACHINE;
        Long machineId = machine.getId();

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.of(machine));
        
        doNothing()
                .when(machineRepository).deleteById(machineId);

        machineService.deleteMachineById(machineId);

        verify(machineRepository).deleteById(machineId);
    }

    @Test
    void when_deleteMachineById_should_throwException_when_machineNotFound() {
        Long machineId = MachineDataBuilder.MACHINE.getId();

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class, () -> machineService.deleteMachineById(machineId));

        verify(machineRepository, never()).deleteById(machineId);
    }
    
    @Test
    void should_addEnergyConsumption() {
        Machine machine = MachineDataBuilder.MACHINE;
        Long machineId = machine.getId();
        CreateEnergyConsumptionDTO createEnergyConsumptionDTO = EnergyConsumptionDataBuilder.CREATE_ENERGY_CONSUMPTION_DTO;
        EnergyConsumption energyConsumption = EnergyConsumptionMapper.fromCreateEnergyConsumptionDTOToEnergyConsumption(createEnergyConsumptionDTO);

        lenient().
            doNothing()
                .when(emailService).sendEmail(anyString(), anyString(), anyString());
        
        when(machineRepository.findById(machineId))
                .thenReturn(Optional.of(machine));

        lenient().when(energyConsumptionRepository.save(energyConsumption))
                .thenReturn(any());

        
        machineService.addEnergyConsumption(machineId, createEnergyConsumptionDTO);

        assertEquals(energyConsumption.getConsumption(), machine.getEnergyConsumptions().get(0).getConsumption());
        verify(energyConsumptionRepository).save(any());
    }

    @Test
    void when_addEnergyConsumption_should_throwException_when_machineNotFound() {
        Long machineId = MachineDataBuilder.MACHINE.getId();

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.empty());

        CreateEnergyConsumptionDTO createEnergyConsumptionDTO = EnergyConsumptionDataBuilder.CREATE_ENERGY_CONSUMPTION_DTO;
        assertThrows(MachineNotFoundException.class, () -> machineService.addEnergyConsumption(machineId, createEnergyConsumptionDTO));

        EnergyConsumption energyConsumption = EnergyConsumptionMapper.fromCreateEnergyConsumptionDTOToEnergyConsumption(createEnergyConsumptionDTO);
        verify(energyConsumptionRepository, never()).save(energyConsumption);
        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }
    
    private MachineInformationDTO assertListMachinesAndReturnFirstMachineInformationDTO(Boolean showConsumptions) {
        Machine machine = MachineDataBuilder.MACHINE;
        List<Machine> machines = Arrays.asList(machine);
        Pageable defaultPageable = DefaultPageableDataBuilder.DEFAULT_PAGEABLE;
        Page<Machine> pageableMachine = new PageImpl<>(machines, defaultPageable, machines.size());

        when(machineRepository.findAll(defaultPageable))
                .thenReturn(pageableMachine);

        Page<MachineInformationDTO> machineInformationDTOPage = machineService.listMachines(defaultPageable, showConsumptions);
        MachineInformationDTO firstMachineInformationDTO = machineInformationDTOPage.stream().findFirst().get();

        assertEquals(machine.getId(), firstMachineInformationDTO.getId());
        assertEquals(machine.getTitle(), firstMachineInformationDTO.getTitle());
        
        verify(machineRepository).findAll(defaultPageable);
        
        return firstMachineInformationDTO;
    }

}