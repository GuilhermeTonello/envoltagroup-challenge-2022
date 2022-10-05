package com.envoltagroup.energy_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MachineNotFoundException extends RuntimeException {
    
    public MachineNotFoundException(String message) {
        super(message);
    }

    public MachineNotFoundException(Long machineId) {
        this(String.format("Machine %d not found", machineId));
    }
    
}
