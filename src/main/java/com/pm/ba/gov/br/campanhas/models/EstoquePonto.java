package com.pm.ba.gov.br.campanhas.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.FetchType;



@Entity
@Table(name = "estoque_ponto")
public class EstoquePonto {
    
    @EmbeddedId
    private EstoquePontoId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPonto")
    @JoinColumn(name = "id_ponto")
    private PontoColeta ponto;
    
    @ManyToOne
    @MapsId("idItem")
    @JoinColumn(name = "id_item")
    private Item item;
    
    @Column(name = "quantidade")
    private Integer quantidade;

    public EstoquePonto() {
    }

    public EstoquePonto(EstoquePontoId id, PontoColeta ponto, Item item, Integer quantidade) {
        this.id = id;
        this.ponto = ponto;
        this.item = item;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public EstoquePontoId getId() {
        return id;
    }

    public void setId(EstoquePontoId id) {
        this.id = id;
    }

    public PontoColeta getPonto() {
        return ponto;
    }

    public void setPonto(PontoColeta ponto) {
        this.ponto = ponto;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
