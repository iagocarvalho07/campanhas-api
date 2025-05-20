package com.pm.ba.gov.br.campanhas.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "transferencia_item")
public class TransferenciaItem {
    
    @EmbeddedId
    private TransferenciaItemId id;
    
    @ManyToOne
    @MapsId("idTransferencia")
    @JoinColumn(name = "id_transferencia")
    private Transferencia transferencia;
    
    @ManyToOne
    @MapsId("idItem")
    @JoinColumn(name = "id_item")
    private Item item;
    
    @Column(name = "quantidade")
    private Integer quantidade;

    public TransferenciaItem() {
    }

    public TransferenciaItem(TransferenciaItemId id, Transferencia transferencia, Item item, Integer quantidade) {
        this.id = id;
        this.transferencia = transferencia;
        this.item = item;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public TransferenciaItemId getId() {
        return id;
    }

    public void setId(TransferenciaItemId id) {
        this.id = id;
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
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

