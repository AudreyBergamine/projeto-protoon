//SERVICE LAYER (RESOURCER -->SERVICE LAYER(AQUI) --> REPOSITORY
package com.proton.services.protocolo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.proton.models.entities.Protocolo;
import com.proton.models.entities.Secretaria;
import com.proton.models.entities.municipe.Municipe;
import com.proton.models.repositories.MunicipeRepository;
//import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.ProtocoloRepository;
import com.proton.models.repositories.SecretariaRepository;

@Service
public class ProtocoloService {

	@Autowired
	private ProtocoloRepository protocoloRepository;

	@Autowired
	private MunicipeRepository municipeRepository;

	@Autowired
	private SecretariaRepository secretariaRepository;

	// Método para encontrar TODOS os protocolos
	public List<Protocolo> findAll() {
		return protocoloRepository.findAll();
	}

	// Método para encontrar protocolo pelo id
	public Protocolo findById(Integer id) {
		Optional<Protocolo> obj = protocoloRepository.findById(id);
		return obj.get();
	}

	// Método para encontrar TODOS protocolos do MUNICIPE
	public List<Protocolo> findByMunicipe(Municipe municipe) {
		return protocoloRepository.findAllByMunicipe(municipe);
	}

	public void updateData(Protocolo entity, Protocolo obj) {
		entity.setSecretaria(obj.getSecretaria());
		entity.setMunicipe(obj.getMunicipe());
		entity.setEndereco(obj.getEndereco());
		entity.setAssunto(obj.getAssunto());
		entity.setDescricao(obj.getDescricao());
		entity.setValor(obj.getValor());
	}

	public Protocolo update(Integer id, Protocolo obj) {
		Protocolo entity = protocoloRepository.getReferenceById(id);
		updateData(entity, obj);
		return protocoloRepository.save(entity);
	}

	public void newProtocolo(Protocolo protocolo) {
		Municipe mun = municipeRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
		Secretaria secretaria = secretariaRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
		protocolo.setAssunto(protocolo.getAssunto());
		protocolo.setDescricao(protocolo.getDescricao());
		protocolo.setData_protocolo(new Date());
		protocolo.setMunicipe(mun);
		protocolo.setEndereco(mun.getEndereco());
		protocolo.setSecretaria(secretaria);
		protocolo.setStatus(1);
		protocolo.setValor(50.0);
		protocoloRepository.save(protocolo);
	}
}