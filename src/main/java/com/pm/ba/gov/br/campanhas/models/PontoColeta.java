package com.pm.ba.gov.br.campanhas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ponto_coleta")
public class PontoColeta {
    
    public PontoColeta() {
    }

    public PontoColeta(String nome, String endereco, Campanha campanha) {
        this.nome = nome;
        this.endereco = endereco;
        this.campanha = campanha;
    }   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto")
    private Integer idPonto;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "endereco")
    private String endereco;
    
    @ManyToOne
    @JoinColumn(name = "id_campanha")
    private Campanha campanha;

    // Getters e Setters
    public Integer getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Integer idPonto) {
        this.idPonto = idPonto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
} 