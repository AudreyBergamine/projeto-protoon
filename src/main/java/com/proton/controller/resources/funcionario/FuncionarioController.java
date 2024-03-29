package com.proton.controller.resources.funcionario;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proton.models.entities.Funcionario;
import com.proton.services.funcionario.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
@CrossOrigin(origins = "http://localhost:3000")
public class FuncionarioController { // Definição dos endpoints RESTful

    @Autowired
    private FuncionarioService service;

    @GetMapping()
    public ResponseEntity<List<Funcionario>> findAll(){
        List<Funcionario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer id){
        Funcionario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdFuncionario()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Integer id, @RequestBody Funcionario obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok(obj);
    }
}
