package com.proton.models.entities.recuper_senha;

import java.util.Date;

import com.proton.models.entities.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "RECUPERARSENHA")
@Data // Esta notação faz todos os métodos da classe através da dependência do Lombok
@EqualsAndHashCode(callSuper = false)
public class RecuperarSenha extends BaseEntity {

  @Id
  private String email;

  private String codigo;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateSendCodigo;

  private String senha;

}
