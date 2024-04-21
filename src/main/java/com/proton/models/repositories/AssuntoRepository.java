package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.Assunto;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {
    
}
