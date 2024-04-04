package com.proton.models.entities.municipe;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proton.models.entities.Endereco;
import com.proton.models.entities.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//O anotação @Entity, diz que é uma tabela do banco de dados. Já @Table, é para incluir o nome da tabela
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Municipe extends User {

    //Implements Serializable é uma interface que transforma os dados do objeto em dados bytes, para transmitir pela web.
    //O Serializable já existe em User... em alguma annotation!

    //O atributo id_municipe, precisa ter as anotações abaixo para dizer que é um id, autoincrement e o seu nome


    @Column(name = "num_CPF", nullable = false, unique = true)
    private String num_CPF;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "data_nascimento", nullable = true)
    private LocalDate data_nascimento;

    
    //A anotação @OneToOne é para dizer que é uma relação 1 para 1 e o @JoinColumn diz que é uma chave estrangeira
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_enderecoFK", referencedColumnName = "id_endereco")
    private Endereco endereco;

 
    //É necessário ter dois construtores, um padrão e um com todos os atributos do municipe
    public Municipe(String nome_municipe, String email, String senha, String num_CPF, LocalDate data_nascimento,
            Endereco id_enderecoFK) {
        this.num_CPF = num_CPF;
        this.data_nascimento = data_nascimento;
        this.endereco = id_enderecoFK;
    }



    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getId_municipe() {
        return getId();
    }


   

    public String getNum_CPF() {
        return num_CPF;
    }

    public void setNum_CPF(String num_CPF) {
        this.num_CPF = num_CPF;
    }

   


    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    //O Hashcode e Equals serve para poder se comparar 2 ids diferentes
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId_municipe() == null) ? 0 : getId_municipe().hashCode());
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
        Municipe other = (Municipe) obj;
        if (getId_municipe() == null) {
            if (other.getId_municipe() != null)
                return false;
        } else if (!getId_municipe().equals(other.getId_municipe()))
            return false;
        return true;
    }

    
}


