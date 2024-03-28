package com.proton.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proton.models.entities.Funcionario;


// TODO Corrigir erro de JPARepository
@Repository("funcionarioRepository")
public class FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}