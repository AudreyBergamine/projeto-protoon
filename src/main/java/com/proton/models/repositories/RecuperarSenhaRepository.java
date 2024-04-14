package com.proton.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proton.models.entities.RecuperarSenha;

public interface RecuperarSenhaRepository extends JpaRepository<RecuperarSenha, String> {
  Optional<RecuperarSenha> findByUsername(String username);
}
