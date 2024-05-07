package com.proton.controller.resources.protocolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proton.models.entities.protocolo.Devolutiva;
import com.proton.services.protocolo.DevolutivaService;

@RestController
@RequestMapping(value = "/protoon/devolutivas")
public class DevolutivaController {

    @Autowired
    private DevolutivaService devolutivaService;

    @GetMapping
    public ResponseEntity<List<Devolutiva>> findAll() {
        List<Devolutiva> devolutivas = devolutivaService.findAll();
        if (!devolutivas.isEmpty()) {
            return ResponseEntity.ok(devolutivas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolutiva> getDevolutivaById(@PathVariable Integer id) {
        Devolutiva devolutiva = devolutivaService.findById(id);
        if (devolutiva != null) {
            return ResponseEntity.ok(devolutiva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/criar-devolutiva/{idFuncionario}/{idProtocolo}")
    public ResponseEntity<Devolutiva> insertDevolutiva(@RequestBody Devolutiva devolutiva,
            @PathVariable Integer idFuncionario,
            @PathVariable Integer idProtocolo) {
        Devolutiva insertedDevolutiva = devolutivaService.insert(devolutiva, idFuncionario, idProtocolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedDevolutiva);
    }
    




}
