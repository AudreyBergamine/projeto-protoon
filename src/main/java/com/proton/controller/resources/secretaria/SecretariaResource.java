package com.proton.controller.resources.secretaria;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proton.models.entities.protocolo.Protocolo;
import com.proton.models.entities.secretaria.Secretaria;
import com.proton.services.secretaria.SecretariaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "protoon/secretaria")
public class SecretariaResource {
    
    @Autowired
    private SecretariaService secretariaService;

    @PostMapping(value = "/{idEnd}")
    public ResponseEntity<Secretaria> insert(@RequestBody Secretaria secretaria, @PathVariable Integer idEnd){
        Secretaria entity = secretariaService.insert(secretaria, idEnd);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest() // Baseado na URL da requisição atual
        .path("/{id}") // Adiciona o ID do recurso criado ao caminho
        .buildAndExpand(entity.getId_secretaria()) // Substitui o {id} pelo ID do usuário criado
        .toUri();
        return ResponseEntity.created(location).body(entity);
    }
    
    @GetMapping // Adicionando a anotação GetMapping para o método findAll
    public ResponseEntity<List<Secretaria>> findAll() {
        List<Secretaria> list = secretariaService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}") // Adicionando o caminho da variável id
    public ResponseEntity<Secretaria> findById(@PathVariable Long id) {
        Secretaria obj = secretariaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/protocolos/{id}")
    public ResponseEntity<List<Protocolo>> findProtocolosBySecretariaId(@PathVariable Long id){
        Secretaria sec = secretariaService.findById(id);
        return ResponseEntity.ok().body(sec.getProtocolos());

    }
}

