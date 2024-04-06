// INTERFACE DO ENDEREÇO QUE EXTENDE O JPA REPOSITORY PARA ACESSAR OS METODOS DO JPA REPOSITORY
// ESSA INTERFACE É USADA PARA ACESSAR OS DADOS DO BANCO DE DADOS

package com.proton.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.Protocolo;
import com.proton.models.entities.municipe.Municipe;

public interface ProtocoloRepository extends JpaRepository<Protocolo, Integer> {
    List<Protocolo> findAllByMunicipe(Municipe municipe);
    
}
