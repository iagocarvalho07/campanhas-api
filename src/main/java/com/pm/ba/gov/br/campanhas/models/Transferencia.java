package com.pm.ba.gov.br.campanhas.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "transferencia")
public class Transferencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Integer idTransferencia;
    
    @ManyToOne
    @JoinColumn(name = "id_ponto")
    private PontoColeta ponto;
    
    @ManyToOne
    @JoinColumn(name = "id_central")
    private CentralColeta central;
    
    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;
    
    @Column(name = "status")
    private String status;
    
    @OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
    private Set<TransferenciaItem> itens;

    // Getters e Setters
    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public PontoColeta getPonto() {
        return ponto;
    }

    public void setPonto(PontoColeta ponto) {
        this.ponto = ponto;
    }

    public CentralColeta getCentral() {
        return central;
    }

    public void setCentral(CentralColeta central) {
        this.central = central;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<TransferenciaItem> getItens() {
        return itens;
    }

    public void setItens(Set<TransferenciaItem> itens) {
        this.itens = itens;
    }
} 