package com.proton.services.municipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.proton.models.entities.Endereco;
import com.proton.models.entities.municipe.Municipe;
import com.proton.models.entities.municipe.TokenReqRes;
import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.UserRepository;


@Component
public class MunicipeService {

    @Autowired // Para que o Spring faça essa injeção de Dependência do Repository
    private MunicipeRepository repository;

    @Autowired 
    JwtTokenService jwtTokenService; //Serviço q lida na validação e criação do token

    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder; //Serviço que lida com o hash da senha

    //Método que retorna uma lista Json com todos os municipes
    public List<Municipe> findAll(){
        return repository.findAll();
    }

    //Método que retorna um objeto Json muncipe com base no id inserido
    public Municipe findById(Integer id){
        Optional<Municipe> obj = repository.findById(id);
        return obj.get();
    }

    //Método que insere um municipe novo no banco de dados, junto com o endereço
    public Municipe insert(Municipe obj) {
        //Faz o relacionamento de municipe e endereço
        Endereco endereco = obj.getEndereco();
        endereco.setMunicipe(obj);
        obj.setEndereco(endereco);

        //Diz a permissão do municipe no código
        String role = "MUNICIPE";
        obj.setRole(role);

        //Encripta a senha
        String hashedPassword = bCryptPasswordEncoder.encode(obj.getSenha());
        obj.setSenha(hashedPassword);

		return repository.save(obj);
	}
    
    // NÃO VAI DELETAR USUÁRIOS >>>> REGRA DE NEGÓCIO 
    // public void delete(Integer id) {
	// 	repository.deleteById(id);	
	// }

    //Método que atualiza as informações do municipe
    public Municipe update(Integer id, Municipe obj) {
		Municipe entity = repository.getReferenceById(id); //instancia o usuário sem mexer no banco de dados
		updateData(entity, obj);
			return repository.save(entity);
	}

    private void updateData(Municipe entity, Municipe obj) {
        entity.setNome_municipe(obj.getNome_municipe());
		entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
        entity.setNum_CPF(obj.getNum_CPF());
        entity.setData_nascimento(obj.getData_nascimento());
        entity.setEndereco(obj.getEndereco());
	}

    public TokenReqRes generateToken(TokenReqRes tokenReqRes) {
        Optional<Municipe> optionalMunicipe = repository.findByEmail(tokenReqRes.getEmail());
        
        if (optionalMunicipe.isPresent()) {
            Municipe databaseMunicipe = optionalMunicipe.get();
            if (new BCryptPasswordEncoder().matches(tokenReqRes.getSenha(), databaseMunicipe.getSenha())) {
                String token = jwtTokenService.generateToken(tokenReqRes.getEmail());
                tokenReqRes.setToken(token);
                tokenReqRes.setExpirationTime("10 min");
                return tokenReqRes;
            } else {
                System.out.println("Senha incorreta!");
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
        return null;
    }
    public String validateToken(TokenReqRes tokenReqRes){
        return jwtTokenService.validateToken(tokenReqRes.getToken());
    }

      public String getRoleByEmail(String email) throws NotFoundException {
        Optional<Municipe> optionalMunicipe = repository.findByEmail(email);
        Municipe municipe = optionalMunicipe.orElseThrow(NotFoundException::new);
        return municipe.getRole();
    }

    public Boolean checkToken(String token){
        if(token == null){
            return false;
        }else{
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenService.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")){
                return true;
            }
            return false;
        }
    }




}
