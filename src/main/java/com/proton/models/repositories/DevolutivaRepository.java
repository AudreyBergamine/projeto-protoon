package com.proton.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proton.models.entities.protocolo.Devolutiva;

@Repository
public interface DevolutivaRepository extends JpaRepository<Devolutiva, Integer> {

    @Query("SELECT p FROM Protocolo p WHERE p.id_protocolo = :idProtocolo")
    List<Devolutiva> findByIdProtocolo(@Param("idProtocolo") int idProtocolo);
}

