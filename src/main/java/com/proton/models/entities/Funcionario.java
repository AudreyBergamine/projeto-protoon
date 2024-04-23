package com.proton.models.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proton.models.entities.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
//TODO: Precisamos adicionar o atributo Reclamação, o Funcionário terá uma lista de reclamações para visualizar
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Funcionario extends User  { 

    @Column(name = "num_CPF", nullable = false, unique = true)
    private String numCPF;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;


   

    @Column(name = "celular", nullable = false)
    private String celular;

    @Column(name = "num_telefone_fixo")
    private String numTelefoneFixo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_enderecoFK", referencedColumnName = "id_endereco")
    private Endereco endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_secretariaFK", referencedColumnName = "id_secretaria")
    private Secretaria secretaria;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_departamentoFK", referencedColumnName = "id_departamento")
    // private Departamento departamento;
    
    // Getters
    public Integer getIdFuncionario() {
        return getId();
    }
   
    public String getNumCPF() {
        return numCPF;
    }

    public LocalDate getDataNascimento() {
        return data_nascimento;
    }

    public String getSenha() {
        return super.getSenha();
    }

    public String celular() {
        return celular;
    }

    public String getNumTelefoneFixo() {
        return numTelefoneFixo;
    }



    // SETTERS
    public void setIdFuncionario(Integer idFuncionario) {
        this.setId(idFuncionario); ;
    }

   

    public void setNumCPF(String numCPF) {
        this.numCPF = numCPF;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.data_nascimento = dataNascimento;
    }




    public void setSenha(String senha) {
        setSenha(senha);
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setNumTelefoneFixo(String numTelefoneFixo) {
        this.numTelefoneFixo = numTelefoneFixo;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdFuncionario() == null) ? 0 : getIdFuncionario().hashCode());
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
        if (getIdFuncionario() == null) {
            if (other.getIdFuncionario() != null)
                return false;
        } else if (!getIdFuncionario().equals(other.getIdFuncionario()))
            return false;
        return true;
    }

}
