package com.proton.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.proton.models.entities.municipe.Municipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "protocolo")
public class Protocolo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_protocolo;
	
	@ManyToOne //Associação Muitos para um
	@JoinColumn(name = "id_secretariaFK") //nome da chave estrangeira
	private Secretaria secretaria;
	
	@ManyToOne //Associação Muitos para um
	@JoinColumn(name = "id_departamentoFK") //nome da chave estrangeira
	private Departamento departamento;
	
	@ManyToOne //Associação Muitos para um
	@JoinColumn(name = "id_municipeFK") //nome da chave estrangeira
	private Municipe municipe; // Municipe ou empresa, por enquanto somente municipe
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;	
	
	private String assunto;	
	
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")//Formatação da data e hora
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_protocolo; //Pega o momento da abertura do protocolo, substitui o tipo Date
	
	private String descricao;
	private Integer status;
	private Double valor;
	
	public Protocolo(){
		
	}

	public Protocolo(Integer id_protocolo, Secretaria secretaria, Municipe municipe, Endereco endereco, String assunto,
			Date data_protocolo, String descricao, Integer status, Double valor) {
		super();
		this.id_protocolo = id_protocolo;
		this.secretaria = secretaria;
		this.municipe = municipe;
		this.endereco = endereco;
		this.assunto = assunto;
		this.data_protocolo = data_protocolo;
		this.descricao = descricao;
		this.status = status;
		this.valor = valor;
	}

	public Integer getId_protocolo() {
		return id_protocolo;
	}

	public void setId_protocolo(Integer id_protocolo) {
		this.id_protocolo = id_protocolo;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Municipe getMunicipe() {
		return municipe;
	}

	public void setMunicipe(Municipe municipe) {
		this.municipe = municipe;
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

	public Date getData_protocolo() {
		return data_protocolo;
	}

	public void setData_protocolo(Date date) {
		this.data_protocolo = date;
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
