package com.envoltagroup.energy_management.repositories;

import com.envoltagroup.energy_management.entities.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
}