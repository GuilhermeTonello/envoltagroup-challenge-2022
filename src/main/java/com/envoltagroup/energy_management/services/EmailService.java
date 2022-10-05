package com.envoltagroup.energy_management.services;

import com.envoltagroup.energy_management.configurations.email.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailConfiguration emailConfiguration;

    private final JavaMailSender emailSender;

    @Async
    public void sendEmail(String subject, String content, String... to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfiguration.getEmailSender());
        message.setBcc(to);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }

}
