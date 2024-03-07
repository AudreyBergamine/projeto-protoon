package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.Municipe;
public interface MunicipeRepository extends JpaRepository<Municipe, Integer>  {
    
}
