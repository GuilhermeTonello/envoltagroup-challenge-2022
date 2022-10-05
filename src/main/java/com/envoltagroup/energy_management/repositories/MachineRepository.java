package com.envoltagroup.energy_management.repositories;

import com.envoltagroup.energy_management.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}