package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proton.models.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}