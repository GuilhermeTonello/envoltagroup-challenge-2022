package com.envoltagroup.energy_management.controllers;

import com.envoltagroup.energy_management.dtos.energy_consumption.CreateEnergyConsumptionDTO;
import com.envoltagroup.energy_management.dtos.machine.CreateMachineDTO;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.dtos.machine.UpdateMachineDTO;
import com.envoltagroup.energy_management.services.MachineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.envoltagroup.energy_management.utils.ConstantUtils.MACHINE_CONTROLLER;

@RestController
@RequestMapping(MACHINE_CONTROLLER)
@AllArgsConstructor
public class MachineController {

    private final MachineService machineService;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MachineInformationDTO createMachine(@RequestBody @Valid CreateMachineDTO createMachineDTO) {
        return machineService.createMachine(createMachineDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MachineInformationDTO getMachineById(@PathVariable("id") Long machineId) {
        return machineService.getMachineById(machineId);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<MachineInformationDTO> listMachines(@PageableDefault Pageable pageable,
                                                    @RequestParam(required = false, defaultValue = "false") Boolean expandConsumptions) {
        return machineService.listMachines(pageable, expandConsumptions);
    }
    
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MachineInformationDTO updateMachine(@PathVariable("id") Long machineId,
                                               @RequestBody @Valid UpdateMachineDTO updateMachineDTO) {
        return machineService.updateMachine(machineId, updateMachineDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMachineById(@PathVariable("id") Long machineId) {
        machineService.deleteMachineById(machineId);
    }
    
    @PostMapping("{id}/energy-consumption")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MachineInformationDTO addConsumptionToMachine(@PathVariable("id") Long machineId,
                                                         @RequestBody @Valid CreateEnergyConsumptionDTO createEnergyConsumptionDTO) {
        return machineService.addEnergyConsumption(machineId, createEnergyConsumptionDTO);
    }
    
}

