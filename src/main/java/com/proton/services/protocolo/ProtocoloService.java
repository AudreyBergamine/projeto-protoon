//SERVICE LAYER (RESOURCER -->SERVICE LAYER(AQUI) --> REPOSITORY
package com.proton.services.protocolo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proton.models.entities.Protocolo;
import com.proton.models.repositories.ProtocoloRepository;



// Esta classe representa um serviço responsável por operações relacionadas a secretaria
@Service
public class ProtocoloService {
	
	@Autowired
	private ProtocoloRepository repository; // Repositório de dados para acesso a secretaria
	
	// Método para encontrar todos as secretarias
	public List<Protocolo> findAll(){
		return repository.findAll(); // Retorna todos as secretarias armazenadas no banco de dados
	}
	
	public Protocolo findById(Long id) {
		Optional <Protocolo> obj = repository.findById(id);
		return obj.get();
	}
}