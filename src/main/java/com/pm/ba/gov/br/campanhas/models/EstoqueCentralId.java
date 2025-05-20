package com.pm.ba.gov.br.campanhas.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class EstoqueCentralId implements Serializable {
    private Integer idCentral;
    private Integer idItem;

    public EstoqueCentralId() {
    }

    public EstoqueCentralId(Integer idCentral, Integer idItem) {
        this.idCentral = idCentral;
        this.idItem = idItem;
    }

    public Integer getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
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
        EstoqueCentralId that = (EstoqueCentralId) o;
        return Objects.equals(idCentral, that.idCentral) &&
               Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCentral, idItem);
    }
} 