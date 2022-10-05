package com.envoltagroup.energy_management.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "machines")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @OneToMany(fetch = FetchType.LAZY, 
            mappedBy = "machine", 
            cascade = CascadeType.ALL)
    @Builder.Default
    private List<EnergyConsumption> energyConsumptions = new ArrayList<>();
    
    @Column(name = "min_energy_in", nullable = false)
    private BigDecimal minimumEnergyInput;
    
    @Column(name = "max_energy_in", nullable = false)
    private BigDecimal maximumEnergyInput;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return Objects.equals(id, machine.id) && Objects.equals(title, machine.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
