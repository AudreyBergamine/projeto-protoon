package com.proton.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailTexto(String destinatario, String titulo, String mensagem) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(destinatario);
            message.setSubject(titulo);
            message.setText(mensagem);
            
            // Envie o email
            emailSender.send(message);

            return "Sucesso";
        } catch (Exception e) {
            return "Falha";
        }
    }
}
