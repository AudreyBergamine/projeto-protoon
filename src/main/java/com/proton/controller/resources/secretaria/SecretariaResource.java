package com.proton.controller.resources.secretaria;

import com.proton.models.entities.protocolo.Protocolo;
import com.proton.models.entities.secretaria.Secretaria;
import com.proton.services.secretaria.SecretariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SecretariaResource.BASE_PATH)
@RequiredArgsConstructor
public class SecretariaResource {
    
    public static final String BASE_PATH = "protoon/secretaria";
    
    private final SecretariaService secretariaService;

    @PostMapping("/{idEnd}")
    public ResponseEntity<Secretaria> insert(@RequestBody Secretaria secretaria, @PathVariable Integer idEnd) {
        Secretaria entity = secretariaService.insert(secretaria, idEnd);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId_secretaria())
                .toUri();
        return ResponseEntity.created(location).body(entity);
    }
    
    @GetMapping
    public ResponseEntity<List<Secretaria>> findAll() {
        return ResponseEntity.ok(secretariaService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Secretaria> findById(@PathVariable Long id) {
        return ResponseEntity.ok(secretariaService.findById(id));
    }

    @GetMapping("/protocolos/{id}")
    public ResponseEntity<List<Protocolo>> findProtocolosBySecretariaId(@PathVariable Long id) {
        Secretaria sec = secretariaService.findById(id);
        
        if (sec == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sec.getProtocolos());
    }
}
