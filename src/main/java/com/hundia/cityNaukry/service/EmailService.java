package com.hundia.cityNaukry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String to, String resetLink) {
        String subject = "Password Reset Request";
        String body = "To reset your password, please click the following link: " + resetLink +
                      "\n\nThis link will expire in 1 hour.";

        // Create SimpleMailMessage instance
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        // Use the injected mailSender instance to send the email
        mailSender.send(message); // No static reference here, mailSender is an instance variable
    }
}
