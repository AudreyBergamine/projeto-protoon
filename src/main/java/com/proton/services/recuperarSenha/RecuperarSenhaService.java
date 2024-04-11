package com.proton.services.recuperarSenha;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.proton.models.entities.RecuperarSenha;
import com.proton.models.repositories.RecuperarSenhaRepository;

@Component
public class RecuperarSenhaService {

    @Autowired
    private RecuperarSenhaRepository recuperarSenhaRepository;

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String email) {
        // Implemente a lógica para gerar um token único
        String token = UUID.randomUUID().toString();

        // Implemente a lógica para salvar o token, o email do usuário e a data de expiração no banco de dados

        // Crie o corpo do email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperação de senha");
        message.setText("Para recuperar sua senha, clique no seguinte link: http://localhost:3000/reset-password?token=" + token);

        // Envie o email
        emailSender.send(message);
    }

    public List<RecuperarSenha> findAll(){
        return recuperarSenhaRepository.findAll();
    }

    public RecuperarSenha findById(String username){
        Optional<RecuperarSenha> obj = recuperarSenhaRepository.findByUsername(username);
        return obj.get();
    }

    public RecuperarSenha insert(RecuperarSenha obj) {
        return recuperarSenhaRepository.save(obj);
    }

    public void delete(String username) {
		recuperarSenhaRepository.deleteById(username);	
	}    

    public RecuperarSenha update(String username, RecuperarSenha obj) {
		RecuperarSenha entity = recuperarSenhaRepository.getReferenceById(username);
        entity.setToken(obj.getToken());
		updateData(entity, obj);
			return recuperarSenhaRepository.save(entity);
	}

    private void updateData(RecuperarSenha entity, RecuperarSenha obj) {
        entity.setUsername(obj.getUsername());
        entity.setToken(obj.getToken());
	}
}
