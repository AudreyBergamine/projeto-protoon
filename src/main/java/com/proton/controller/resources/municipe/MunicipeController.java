package com.proton.controller.resources.municipe;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proton.models.entities.Municipe;
import com.proton.services.municipe.MunicipeService;

@RestController
@RequestMapping(value = "/municipes")
@CrossOrigin(origins = "http://localhost:3000")
public class MunicipeController {
    @Autowired
   private MunicipeService service;

    //TODO: Remover acesso a lista completa! E retirar retorno do atributo senha!
    @GetMapping()
    public ResponseEntity<List<Municipe>> findAll(){
        List<Municipe> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Municipe> findById(@PathVariable Integer id){
        Municipe obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Municipe> insert(@RequestBody Municipe obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_municipe()).toUri();
		return ResponseEntity.created(uri).body(obj); //Código 201
	}

    //TODO: trocar o método remover para desativar posteriormente!
    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build(); //Resposta para sem conteúdo, código 204
	}

    @PutMapping(value = "{id}")
	public ResponseEntity<Municipe> update(@PathVariable Integer id, @RequestBody Municipe obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok(obj);
	}
    
}
