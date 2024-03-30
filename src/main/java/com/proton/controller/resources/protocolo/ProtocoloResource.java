package com.proton.controller.resources.protocolo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proton.models.entities.Protocolo;
import com.proton.services.protocolo.ProtocoloService;

@RestController
@RequestMapping(value = "/protocolo")
public class ProtocoloResource {
    
    @Autowired
    private ProtocoloService protocoloService;
    
    @GetMapping // Adicionando a anotação GetMapping para o método findAll
    public ResponseEntity<List<Protocolo>> findAll() {
        List<Protocolo> list = protocoloService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}") // Adicionando o caminho da variável id
    public ResponseEntity<Protocolo> findById(@PathVariable Long id) {
        Protocolo obj = protocoloService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}

