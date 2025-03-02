package com.proton.models.enums;

public enum Prioridade {
    BAIXA(7),
    MEDIA(5),
    ALTA(3),
    URGENTE(1);

    private final int diasParaResolver;

    Prioridade(int diasParaResolver) {
        this.diasParaResolver = diasParaResolver;
    }

    public int getDiasParaResolver() {
        return diasParaResolver;
    }
}
