package com.proton.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="RECUPERARSENHA")
@Data //Esta notação faz todos os métodos da classe através da dependência do Lombok
public class RecuperarSenha {

  @Id
  private String username;

  private String token;

}
