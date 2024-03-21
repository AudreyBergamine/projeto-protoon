package com.proton.controller.resources.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.proton.models.entities.User;
import com.proton.services.user.UserService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired // Para que o Spring faça essa injeção de Dependência do Service
    private UserService userService;
      
    // Método que responde á requisição do tipo GET do HTTP
    @GetMapping()
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list); // Retornar a resposta de sucesso do HTTP e no corpo da resposta vai incluir a lista
    }

    @GetMapping(value = "/{username}") // A requisição vai aceitar um ID dentro do URL
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findById(@PathVariable String username){
        User obj = userService.findById(username);
        return ResponseEntity.ok().body(obj);
    }

    // Método que responde á requisição do tipo POST do HTTP
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(obj.getUsername()).toUri();
		return ResponseEntity.created(uri).body(obj); //Código 201
	}
    
    @DeleteMapping(value = "/{username}")
    // @PreAuthorize("hasRole('ADMIN')") //Tem que configurar ainda para permitir somente Admim
	public ResponseEntity<Void> delete(@PathVariable String username){
		userService.delete(username);
		return ResponseEntity.noContent().build(); //Resposta para sem conteúdo, código 204
	}
    
    @PutMapping(value = "{username}") // A requisição vai aceitar um ID dentro do URL
	public ResponseEntity<User> update(@PathVariable String username, @RequestBody User obj){
        User user = userService.findById(username);
        obj.setRole(user.getRole()); // Setar a role do usuário encontrado no objeto a ser atualizado
        obj = userService.update(username, obj);
        return ResponseEntity.ok(obj);
	}
}
