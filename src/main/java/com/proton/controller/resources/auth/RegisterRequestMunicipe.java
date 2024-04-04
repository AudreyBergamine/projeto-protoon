package com.proton.controller.resources.auth;

import java.time.LocalDate;

import com.proton.models.entities.Endereco;
import com.proton.models.entities.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestMunicipe {

  private String nome;
  private String email;
  private String senha;
  private String num_CPF;
  private LocalDate data_nascimento;
  private Endereco endereco;
  
}
