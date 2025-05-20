package com.pm.ba.gov.br.campanhas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "central_coleta")
public class CentralColeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_central")
    private Integer idCentral;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "endereco")
    private String endereco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campanha")
    private Campanha campanha;

    public CentralColeta() {
    }

    public CentralColeta(String nome, String endereco, Campanha campanha) {
        this.nome = nome;
        this.endereco = endereco;
        this.campanha = campanha;
    }

    // Getters e Setters
    public Integer getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
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

	@Override
	public String toString() {
		return "CentralColeta [idCentral=" + idCentral + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
    
    
    

    
} 