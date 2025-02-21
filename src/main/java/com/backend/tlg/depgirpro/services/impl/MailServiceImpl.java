package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(Persona persona, String token) {
        String url = "http://localhost:8080/personas/verify?token=" + token;
        String subject = "Completa tu registro";
        String text = "Para confirmar la cuenta, por favor haga click en el link:\n" + url;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(persona.getCorreo());
        message.setSubject(subject);
        message.setText(text);


        mailSender.send(message);
    }
}
