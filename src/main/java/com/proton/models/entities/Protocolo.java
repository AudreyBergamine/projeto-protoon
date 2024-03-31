package com.proton.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proton.models.entities.municipe.Municipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "protocolo")
public class Protocolo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_protocolo;
	
	@ManyToOne //Associação Muitos para um
	@JoinColumn(name = "secretaria") //nome da chave estrangeira
	private Secretaria secretaria;
	
	
	//private String departamento Criar classe ou um enum??
	
	@ManyToOne //Associação Muitos para um
	@JoinColumn(name = "municipe") //nome da chave estrangeira
	private Municipe requerente; // Municipe ou empresa, por enquanto somente municipe
	
	@OneToOne // REVER ISSO
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	
	private String assunto;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")//Formatação da data e hora
	private Instant data_protocolo; //Pega o momento da compra, substitui o tipo Date
	
	private String descricao;
	private Integer status;
	private Double valor;
	
	public Protocolo(){
		
	}

	public Protocolo(Long id_protocolo, Secretaria secretaria, Municipe requerente, Endereco endereco, String assunto,
			Instant data_protocolo, String descricao, Integer status, Double valor) {
		super();
		this.id_protocolo = id_protocolo;
		this.secretaria = secretaria;
		this.requerente = requerente;
		this.endereco = endereco;
		this.assunto = assunto;
		this.data_protocolo = data_protocolo;
		this.descricao = descricao;
		this.status = status;
		this.valor = valor;
	}

	public Long getId_protocolo() {
		return id_protocolo;
	}

	public void setId_protocolo(Long id_protocolo) {
		this.id_protocolo = id_protocolo;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Municipe getRequerente() {
		return requerente;
	}

	public void setRequerente(Municipe requerente) {
		this.requerente = requerente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Instant getData_protocolo() {
		return data_protocolo;
	}

	public void setData_protocolo(Instant data_protocolo) {
		this.data_protocolo = data_protocolo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_protocolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Protocolo other = (Protocolo) obj;
		return Objects.equals(id_protocolo, other.id_protocolo);
	}
	
	
	
}
