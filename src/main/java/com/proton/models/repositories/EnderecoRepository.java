package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
}
