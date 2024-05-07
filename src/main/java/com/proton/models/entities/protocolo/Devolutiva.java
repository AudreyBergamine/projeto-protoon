package com.proton.models.entities.protocolo;

import java.io.Serializable;
import java.time.Instant;

import com.proton.models.entities.funcionario.Funcionario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Devolutiva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_devolutiva;

    @ManyToOne
    @JoinColumn(name = "id_funcionarioFK", referencedColumnName = "id")
    private Funcionario id_funcionario;

    @ManyToOne
    @JoinColumn(name = "id_protocoloFK", referencedColumnName = "id_protocolo")
    private Protocolo id_protocolo;

    private Instant momento_devolutiva;

    @Column(columnDefinition = "TEXT")
    private String devolutiva;

    public Devolutiva(){

    }

    public Devolutiva(Integer id_devolutiva, Funcionario id_funcionario, Protocolo id_protocolo,
            Instant momento_devolutiva, String devolutiva) {
        this.id_devolutiva = id_devolutiva;
        this.id_funcionario = id_funcionario;
        this.id_protocolo = id_protocolo;
        this.momento_devolutiva = momento_devolutiva;
        this.devolutiva = devolutiva;
    }

    public Funcionario getId_funcionario() {
        return id_funcionario;
    }
    public void setId_funcionario(Funcionario id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    public Protocolo getId_protocolo() {
        return id_protocolo;
    }
    public void setId_protocolo(Protocolo id_protocolo) {
        this.id_protocolo = id_protocolo;
    }
    public Instant getMomento_devolutiva() {
        return momento_devolutiva;
    }
    public void setMomento_devolutiva(Instant momento_devolutiva) {
        this.momento_devolutiva = momento_devolutiva;
    }
    public String getDevolutiva() {
        return devolutiva;
    }
    public void setDevolutiva(String devolutiva) {
        this.devolutiva = devolutiva;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_devolutiva == null) ? 0 : id_devolutiva.hashCode());
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
        Devolutiva other = (Devolutiva) obj;
        if (id_devolutiva == null) {
            if (other.id_devolutiva != null)
                return false;
        } else if (!id_devolutiva.equals(other.id_devolutiva))
            return false;
        return true;
    }
}
