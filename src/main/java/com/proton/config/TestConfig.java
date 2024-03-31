package com.proton.config;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.proton.models.entities.Endereco;
import com.proton.models.entities.Protocolo;
import com.proton.models.entities.Secretaria;
import com.proton.models.entities.municipe.Municipe;
import com.proton.models.repositories.EnderecoRepository;
import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.ProtocoloRepository;
import com.proton.models.repositories.SecretariaRepository;

//Por enquanto, isso vai servir de data base seeding
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired/// Serve para injetar automaticamente uma instância de OrderRepository nessa variável orderRepository sempre que essa classe for criada
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private MunicipeRepository municipeRepository;
	
	@Autowired
	private SecretariaRepository secretariaRepository;
	
	@Autowired
	private ProtocoloRepository protocoloRepository;

	@Override
	public void run(String... args) throws Exception {
		// Objetos a serem enviado para o banco h2 (Id nulo pq o banco de dados gera automaticamente
		
		Endereco end1 = new Endereco(null, "apartamento", "54321-876", "Avenida Secundária", "Casa 202",
	            "456", "Edifício B", "Bairro Novo", "Rio de Janeiro", "RJ", "Brasil");
		
		Endereco end2 = new Endereco(null, "escritório", "98765-432", "Praça Central", "Sala 301",
	            "789", "Torre C", "Centro Histórico", "Porto Alegre", "RS", "Brasil");

		Endereco end3 = new Endereco(null, "casa", "11111-222", "Rua dos Fundos", "Casa 303",
	            "101", "Bloco C", "Periferia", "Belo Horizonte", "MG", "Brasil");
		
		
		Secretaria sec1 = new Secretaria(null, "Secretaria de Educação", "Ana Silva", "ana@example.com", "senha123", end2);

		Secretaria sec2 = new Secretaria(null, "Secretaria de Saúde", "Carlos Santos", "carlos@example.com", "senha456", end3);

		Secretaria sec3 = new Secretaria(null, "Secretaria de Meio Ambiente", "Mariana Oliveira", "mariana@example.com", "senha789", end1);

		// Municipe mun1= new Municipe(null, "Fulano", "fulano@example.com", "senha123", "123.456.789-10",
        //         LocalDate.of(1990, 5, 15), end3);

		// municipeRepository.save(mun1);
		
		// Protocolo prot1 = new Protocolo(null, sec1, mun1, end2, "Assunto do protocolo", Instant.now(), "Descrição do protocolo", 1, 100.0);

		
		
		// Manda para o banco de dados
		enderecoRepository.saveAll(Arrays.asList(end1,end2,end3));
		secretariaRepository.saveAll(Arrays.asList(sec1,sec2,sec3));
		// protocoloRepository.saveAll(Arrays.asList(prot1));
		
		
		


	}
	
}
