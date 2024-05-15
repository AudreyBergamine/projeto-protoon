package com.proton.models.redirecionamento;

import com.proton.models.entities.protocolo.Protocolo;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class Redirecionamento {
    private boolean aprovado;
    private String nova_secretaria;

    @Lob
    @Column(name = "descricao_transferencia", length = 120, nullable = true) // Define o tamanho máximo do campo
    private String descricao_transferencia;


    @Lob
    @Column(name = "descricao_recusa", length = 120, nullable = true) // Define o tamanho máximo do campo
    private String descricao_recusa;


    private Protocolo protocolo;

    // private Funcionario funcionario;
}
