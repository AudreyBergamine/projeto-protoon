package com.proton.models.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "secretaria")
public class Secretaria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_secretaria;
	
	private String nome_secretaria;
	private String nome_responsavel;
	private String email;
	private String senha;
	

	@OneToOne
	@JoinColumn(name = "id_enderecoFK")
	private Endereco endereco;
    
	private Secretaria(){
		
	}

	public Secretaria(Long id_secretaria, String nome_secretaria, String nome_responsavel, String email, String senha, 
			Endereco id_enderecoFK ) {
		super();
		this.id_secretaria = id_secretaria;
		this.nome_secretaria = nome_secretaria;
		this.nome_responsavel = nome_responsavel;
		this.email = email;
		this.senha = senha;	
		this.endereco = id_enderecoFK;
	}

	public Long getId_secretaria() {
		return id_secretaria;
	}

	public void setId_secretaria(Long id_secretaria) {
		this.id_secretaria = id_secretaria;
	}

	public String getNome_secretaria() {
		return nome_secretaria;
	}

	public void setNome_secretaria(String nome_secretaria) {
		this.nome_secretaria = nome_secretaria;
	}

	public String getNome_responsavel() {
		return nome_responsavel;
	}

	public void setNome_responsavel(String nome_responsavel) {
		this.nome_responsavel = nome_responsavel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getId_endereco() {
		return endereco;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id_secretaria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secretaria other = (Secretaria) obj;
		return Objects.equals(id_secretaria, other.id_secretaria);
	}
	
	
}
