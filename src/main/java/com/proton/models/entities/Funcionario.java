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

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable  {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    @Column(name = "id_departamentoFK")
    private Integer idDepartamentoFK;

    @Column(name = "id_secretariaFK")
    private Integer idSecretariaFK;

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

    // getters and setters

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
