package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.assunto.Assunto;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {
    
}
