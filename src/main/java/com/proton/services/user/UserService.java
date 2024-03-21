package com.proton.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.proton.models.entities.User;
import com.proton.models.repositories.UserRepository;


@Component
public class UserService {

    @Autowired // Para que o Spring faça essa injeção de Dependência do Repository
    private UserRepository userRepository;

    //Método que retorna uma lista Json com todos os users
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //Método que retorna um objeto Json user com base no id inserido
    public User findById(String username){
        Optional<User> obj = userRepository.findByUsername(username);
        return obj.get();
    }

    //Método que insere um user novo no banco de dados, junto com o endereço
    public User insert(User obj) {
        String hashedPassword = new BCryptPasswordEncoder().encode(obj.getPassword());
        obj.setPassword(hashedPassword);
        return userRepository.save(obj); // Salva o usuário com a Role definida
    }

    // NÃO VAI DELETAR USUÁRIOS >>>> REGRA DE NEGÓCIO 
    public void delete(String username) {
		userRepository.deleteById(username);	
	}
    

    //Método que atualiza as informações do user
    public User update(String username, User obj) {
		User entity = userRepository.getReferenceById(username); //instancia o usuário sem mexer no banco de dados
		updateData(entity, obj);
			return userRepository.save(entity);
	}

    private void updateData(User entity, User obj) {
		// TODO Auto-generated method stub
        entity.setUsername(obj.getUsername());
        entity.setPassword(obj.getPassword());
        entity.setRole(obj.getRole());
	}    
}
