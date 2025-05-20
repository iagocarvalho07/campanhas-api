package com.pm.ba.gov.br.campanhas.models;

public enum CategoriaItem {
    ALIMENTO("Alimento"),
    VESTUARIO("Vestuário"),
    HIGIENE("Higiene"),
    BRINQUEDO("Brinquedo"),
    ELETRODOMESTICO("Eletrodoméstico"),
    MOVEIS("Móveis"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 