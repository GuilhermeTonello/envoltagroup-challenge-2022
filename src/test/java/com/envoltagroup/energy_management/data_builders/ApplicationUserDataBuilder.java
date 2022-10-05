package com.envoltagroup.energy_management.data_builders;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationUserDataBuilder {

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public @interface MockedAdmin {}

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username = "robson", roles = {"EMPLOYEE"})
    public @interface MockedEmployee {}
    
}
