package com.oficinadobrito.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailsService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public EmailsService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public String enviarEmail(String destinatario,String assunto, String message){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(message);
            this.javaMailSender.send(simpleMailMessage);
            return "Email enviado";
        }catch (Exception e){
            return "Erro ao enviar o email "+ e.getLocalizedMessage();
        }
    }
}
