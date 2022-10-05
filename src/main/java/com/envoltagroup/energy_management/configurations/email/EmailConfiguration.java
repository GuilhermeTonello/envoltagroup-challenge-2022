package com.envoltagroup.energy_management.configurations.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {

    @Value("${spring.mail.username}")
    private String emailSender;

    public String getEmailSender() {
        return emailSender;
    }
}
