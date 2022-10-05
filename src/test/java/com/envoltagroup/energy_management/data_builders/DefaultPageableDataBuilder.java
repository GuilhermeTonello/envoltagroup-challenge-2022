package com.envoltagroup.energy_management.data_builders;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultPageableDataBuilder {
    
    public static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.ASC, "id");
    public static final Pageable DEFAULT_PAGEABLE = PageRequest.of(0, 10, DEFAULT_SORT);
    
}
