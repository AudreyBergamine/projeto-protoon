//SERVICE LAYER (RESOURCER -->SERVICE LAYER(AQUI) --> REPOSITORY
package com.proton.services.protocolo;

import java.time.LocalDate;
// import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.proton.models.entities.Protocolo;
// import com.proton.models.entities.Secretaria;
import com.proton.models.entities.municipe.Municipe;
// import com.proton.models.repositories.MunicipeRepository;
//import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.ProtocoloRepository;
// import com.proton.models.repositories.SecretariaRepository;

@Service
public class ProtocoloService {

	@Autowired
	private ProtocoloRepository protocoloRepository;

	// @Autowired
	// private MunicipeRepository municipeRepository;

	// @Autowired
	// private SecretariaRepository secretariaRepository;

	private final JdbcTemplate jdbcTemplate; // Para fazer consultas no sql

	@Autowired
	public ProtocoloService(JdbcTemplate jdbcTemplate) { // Construtor para o Spring injetar o jdbcTemplate no Protocolo
		this.jdbcTemplate = jdbcTemplate;
	}

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

	@SuppressWarnings("unused")
	public String gerarNumeroProtocolo() {
		String anoAtual = String.valueOf(LocalDate.now().getYear());
		String sql = "SELECT MAX(CAST(SUBSTRING(numero_protocolo, 1, POSITION('/' IN numero_protocolo) - 1) AS UNSIGNED)) FROM Protocolo WHERE numero_protocolo LIKE ?";
		Integer ultimoNumero = jdbcTemplate.queryForObject(sql, Integer.class, "%/" + anoAtual);
	
		if (ultimoNumero == null) {
			return "001/" + anoAtual;
		} else {
			int proximoNumeroProtocolo = ultimoNumero + 1;
			String novoNumeroProtocolo = String.format("%03d", proximoNumeroProtocolo);
	
			return novoNumeroProtocolo + "/" + anoAtual;
		}
	}
	

	// public void novoProtocolo(Protocolo protocolo) {
	// Municipe mun = municipeRepository.findAll(PageRequest.of(0,
	// 1)).getContent().get(0);
	// Secretaria secretaria = secretariaRepository.findAll(PageRequest.of(0,
	// 1)).getContent().get(0);
	// protocolo.setData_protocolo(new Date());
	// protocolo.setMunicipe(mun);
	// protocolo.setEndereco(mun.getEndereco());
	// protocolo.setSecretaria(secretaria);
	// protocolo.setStatus(1);
	// protocolo.setValor(50.0);
	// protocoloRepository.save(protocolo);
	// }
}