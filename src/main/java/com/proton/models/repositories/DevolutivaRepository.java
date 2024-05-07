package com.proton.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proton.models.entities.protocolo.Devolutiva;

@Repository
public interface DevolutivaRepository extends JpaRepository<Devolutiva, Integer> {

    List<Devolutiva> findAll();

    Optional<Devolutiva> findById(Integer id);
}

