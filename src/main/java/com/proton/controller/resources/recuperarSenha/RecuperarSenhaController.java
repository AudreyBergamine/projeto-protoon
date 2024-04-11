package com.proton.controller.resources.recuperarSenha;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proton.models.entities.RecuperarSenha;
import com.proton.services.recuperarSenha.RecuperarSenhaService;

@RestController
@RequestMapping(value = "/protoon/municipe/municipes/reset-password")
public class RecuperarSenhaController {

    @Autowired
    private RecuperarSenhaService recuperarSenhaService;

    @PostMapping("/")
    public void requestPasswordReset(@RequestBody String email) {
        recuperarSenhaService.sendPasswordResetEmail(email);
    }

    @PutMapping(value = "{username}") // A requisição vai aceitar um ID dentro do UR
    public ResponseEntity<RecuperarSenha> update(@PathVariable String username, @RequestBody RecuperarSenha obj) {
        RecuperarSenha recuperarSenha = recuperarSenhaService.findById(username);
        if (recuperarSenha == null) {
            return ResponseEntity.notFound().build();
        }

        // Gere um token único
        String token = UUID.randomUUID().toString();

        // Salve o token no objeto Municipe
        recuperarSenha.setToken(token);

        // Salve as alterações no banco de dados
        recuperarSenha = recuperarSenhaService.update(username, recuperarSenha);

        // Retorne o objeto Municipe com o token gerado
        return ResponseEntity.ok(recuperarSenha);
    }

}
