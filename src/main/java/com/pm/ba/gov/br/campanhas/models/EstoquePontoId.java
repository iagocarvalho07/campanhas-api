package com.pm.ba.gov.br.campanhas.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class EstoquePontoId implements Serializable {
    private Integer idPonto;
    private Integer idItem;

    public EstoquePontoId() {
    }

    public EstoquePontoId(Integer idPonto, Integer idItem) {
        this.idPonto = idPonto;
        this.idItem = idItem;
    }

    public Integer getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Integer idPonto) {
        this.idPonto = idPonto;
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
        EstoquePontoId that = (EstoquePontoId) o;
        return Objects.equals(idPonto, that.idPonto) &&
               Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPonto, idItem);
    }
} 