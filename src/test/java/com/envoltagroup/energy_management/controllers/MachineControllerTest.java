package com.envoltagroup.energy_management.controllers;

import com.envoltagroup.energy_management.data_builders.ApplicationUserDataBuilder;
import com.envoltagroup.energy_management.data_builders.MachineDataBuilder;
import com.envoltagroup.energy_management.dtos.machine.MachineInformationDTO;
import com.envoltagroup.energy_management.services.MachineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.envoltagroup.energy_management.utils.ConstantUtils.MACHINE_CONTROLLER;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MachineController.class)
@AutoConfigureMockMvc
class MachineControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private MachineService machineService;
    
    @Test
    @ApplicationUserDataBuilder.MockedAdmin
    void when_getMachineById_asAdmin_then_returnOneMachine() throws Exception {
        MachineInformationDTO machineInformationDTO = MachineDataBuilder.MACHINE_INFORMATION_DTO;
        
        when(machineService.getMachineById(1L))
                .thenReturn(machineInformationDTO);
        
        mockMvc.perform(get(MACHINE_CONTROLLER + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(machineInformationDTO.getId().intValue())))
                .andExpect(jsonPath("$.title", is(machineInformationDTO.getTitle())));
    }

    @Test
    void when_getMachineById_withoutToken_then_returnUnauthorized() throws Exception {
        mockMvc.perform(get(MACHINE_CONTROLLER + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        
        verify(machineService, never()).getMachineById(1L);
    }
    
}