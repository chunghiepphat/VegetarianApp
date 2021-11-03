package com.example.hiepphat.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("EmailService")
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void sendEmail(SimpleMailMessage email) {
            mailSender.send(email);
    }
    @Override
    public String random() {
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            return String.format("%06d", number);
    }
}
