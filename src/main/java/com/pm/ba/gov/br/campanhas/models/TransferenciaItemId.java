package com.pm.ba.gov.br.campanhas.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class TransferenciaItemId implements Serializable {
    private Integer idTransferencia;
    private Integer idItem;

    public TransferenciaItemId() {
    }

    public TransferenciaItemId(Integer idTransferencia, Integer idItem) {
        this.idTransferencia = idTransferencia;
        this.idItem = idItem;
    }

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferenciaItemId that = (TransferenciaItemId) o;
        return Objects.equals(idTransferencia, that.idTransferencia) &&
               Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransferencia, idItem);
    }
} 