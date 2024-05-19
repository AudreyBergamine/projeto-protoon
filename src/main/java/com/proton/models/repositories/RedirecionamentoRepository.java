package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.redirecionamento.Redirecionamento;

public interface RedirecionamentoRepository extends JpaRepository<Redirecionamento, Integer> {
    
}
