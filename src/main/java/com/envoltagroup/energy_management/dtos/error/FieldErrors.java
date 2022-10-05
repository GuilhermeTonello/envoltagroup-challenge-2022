package com.envoltagroup.energy_management.dtos.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrors {
    
    private String field;
    private List<String> errors = new ArrayList<>();
    
    public void addError(String error) {
        errors.add(error);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldErrors that = (FieldErrors) o;

        return this.field != null && this.field.equals(that.field);
    }

    @Override
    public int hashCode() {
        return field != null ? field.hashCode() : 0;
    }
}
