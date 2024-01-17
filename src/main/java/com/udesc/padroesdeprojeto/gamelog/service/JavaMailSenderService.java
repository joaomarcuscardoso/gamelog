package com.udesc.padroesdeprojeto.gamelog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String destination, String subject, String body){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destination);
        email.setSubject(subject);
        email.setText(body);
        mailSender.send(email);
    }
}
