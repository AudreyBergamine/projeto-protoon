package com.proton.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//TODO: Precisamos adicionar o atributo Reclamação, o Funcionário terá uma lista de reclamações para visualizar
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable  {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    //TODO: Precisa-se relacionar a entidade Funcionário com Departamento 
    @Column(name = "id_departamentoFK")
    private Integer idDepartamentoFK;

    //TODO: Precisa-se relacionar a entidade Funcionário com Secretaria 
    @Column(name = "id_secretariaFK")
    private Integer idSecretariaFK;

    //TODO: Precisa-se relacionar a entidade Funcionário com Endereco 1x1 
    @Column(name = "id_enderecoFK")
    private Integer idEnderecoFK;

    @Column(name = "nome_funcionario", nullable = false)
    private String nomeFuncionario;

    @Column(name = "num_CPF")
    private String numCPF;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cargo_funcionario")
    private String cargoFuncionario;

    //TODO: Não temos a entidade departamento, teriamos que fazê-la ou remover esse atributo caso formos fazê-la
    @Column(name = "email_departamento")
    private String emailDepartamento;

    @Column(name = "senha")
    private String senha;

    @Column(name = "num_telefone_movel", nullable = false)
    private String numTelefoneMovel;

    @Column(name = "num_telefone_fixo")
    private String numTelefoneFixo;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario, Integer idDepartamentoFK, Integer idSecretariaFK, Integer idEnderecoFK,
            String nomeFuncionario, String numCPF, LocalDate dataNascimento, String cargoFuncionario,
            String emailDepartamento, String senha, String numTelefoneMovel, String numTelefoneFixo) {
        this.idFuncionario = idFuncionario;
        this.idDepartamentoFK = idDepartamentoFK;
        this.idSecretariaFK = idSecretariaFK;
        this.idEnderecoFK = idEnderecoFK;
        this.nomeFuncionario = nomeFuncionario;
        this.numCPF = numCPF;
        this.dataNascimento = dataNascimento;
        this.cargoFuncionario = cargoFuncionario;
        this.emailDepartamento = emailDepartamento;
        this.senha = senha;
        this.numTelefoneMovel = numTelefoneMovel;
        this.numTelefoneFixo = numTelefoneFixo;
    }

    // Getters
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public Integer getIdDepartamentoFK() {
        return idDepartamentoFK;
    }

    public Integer getIdSecretariaFK() {
        return idSecretariaFK;
    }

    public Integer getIdEnderecoFK() {
        return idEnderecoFK;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getNumCPF() {
        return numCPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public String getEmailDepartamento() {
        return emailDepartamento;
    }

    public String getSenha() {
        return senha;
    }

    public String getNumTelefoneMovel() {
        return numTelefoneMovel;
    }

    public String getNumTelefoneFixo() {
        return numTelefoneFixo;
    }

    // SETTERS
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setIdDepartamentoFK(Integer idDepartamentoFK) {
        this.idDepartamentoFK = idDepartamentoFK;
    }

    public void setIdSecretariaFK(Integer idSecretariaFK) {
        this.idSecretariaFK = idSecretariaFK;
    }

    public void setIdEnderecoFK(Integer idEnderecoFK) {
        this.idEnderecoFK = idEnderecoFK;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setNumCPF(String numCPF) {
        this.numCPF = numCPF;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public void setEmailDepartamento(String emailDepartamento) {
        this.emailDepartamento = emailDepartamento;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNumTelefoneMovel(String numTelefoneMovel) {
        this.numTelefoneMovel = numTelefoneMovel;
    }

    public void setNumTelefoneFixo(String numTelefoneFixo) {
        this.numTelefoneFixo = numTelefoneFixo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (idFuncionario == null) {
            if (other.idFuncionario != null)
                return false;
        } else if (!idFuncionario.equals(other.idFuncionario))
            return false;
        return true;
    }

}
