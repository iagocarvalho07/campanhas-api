package com.pm.ba.gov.br.campanhas.dto;

import com.pm.ba.gov.br.campanhas.models.EstoquePonto;
import com.pm.ba.gov.br.campanhas.models.CategoriaItem;

public class EstoquePontoDTO {
    private Integer idPonto;
    private String nomePonto;
    private Integer idItem;
    private String nomeItem;
    private CategoriaItem categoriaItem;
    private Integer quantidade;

    public EstoquePontoDTO(EstoquePonto estoque) {
        this.idPonto = estoque.getPonto().getIdPonto();
        this.nomePonto = estoque.getPonto().getNome();
        this.idItem = estoque.getItem().getIdItem();
        this.nomeItem = estoque.getItem().getNome();
        this.categoriaItem = estoque.getItem().getCategoria();
        this.quantidade = estoque.getQuantidade();
    }

    // Getters e Setters
    public Integer getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Integer idPonto) {
        this.idPonto = idPonto;
    }

    public String getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public CategoriaItem getCategoriaItem() {
        return categoriaItem;
    }

    public void setCategoriaItem(CategoriaItem categoriaItem) {
        this.categoriaItem = categoriaItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
} 