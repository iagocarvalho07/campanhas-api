package com.pm.ba.gov.br.campanhas.dto;

import com.pm.ba.gov.br.campanhas.models.CategoriaItem;

public class itemDTO {
    private String nome;
    private CategoriaItem categoria;
    private Integer unidade;

    public itemDTO() {
    }

    public itemDTO(String nome, CategoriaItem categoria, Integer unidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.unidade = unidade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaItem categoria) {
        this.categoria = categoria;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }
}
