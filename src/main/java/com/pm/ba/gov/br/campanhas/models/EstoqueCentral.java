package com.pm.ba.gov.br.campanhas.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name = "estoque_central")
public class EstoqueCentral {
    
    @EmbeddedId
    private EstoqueCentralId id;
    
    @ManyToOne
    @MapsId("idCentral")
    @JoinColumn(name = "id_central")
    private CentralColeta central;
    
    @ManyToOne
    @MapsId("idItem")
    @JoinColumn(name = "id_item")
    private Item item;
    
    @Column(name = "quantidade")
    private Integer quantidade;

    public EstoqueCentral() {
    }

    public EstoqueCentral(EstoqueCentralId id, CentralColeta central, Item item, Integer quantidade) {
        this.id = id;
        this.central = central;
        this.item = item;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public EstoqueCentralId getId() {
        return id;
    }

    public void setId(EstoqueCentralId id) {
        this.id = id;
    }

    public CentralColeta getCentral() {
        return central;
    }

    public void setCentral(CentralColeta central) {
        this.central = central;
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