//package com.jspm.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendResetPasswordEmail(String toEmail, String token) {
//        String subject = "Reset Your Password";
//        String resetLink = "http://localhost:3000/reset-password?token=" + token;
//
//        String body = "Click the link to reset your password: " + resetLink;
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//    }
//}
