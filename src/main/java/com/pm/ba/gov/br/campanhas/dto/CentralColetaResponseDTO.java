package com.pm.ba.gov.br.campanhas.dto;

public class CentralColetaResponseDTO {
    private Integer idCentral;
    private String nome;
    private String endereco;
    private Integer idCampanha;

    public CentralColetaResponseDTO(Integer idCentral, String nome, String endereco, Integer idCampanha) {
        this.idCentral = idCentral;
        this.nome = nome;
        this.endereco = endereco;
        this.idCampanha = idCampanha;
    }

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

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }
} 