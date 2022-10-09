package com.envoltagroup.energy_management.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "energy_consumption")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "consumption", nullable = false)
    private BigDecimal consumption;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Machine machine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergyConsumption that = (EnergyConsumption) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(consumption, that.consumption) && Objects.equals(machine, that.machine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, consumption, machine);
    }
}
