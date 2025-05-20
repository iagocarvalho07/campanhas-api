package com.pm.ba.gov.br.campanhas.dto;

public class CentralColetaDTO {

    private Integer idCentral;

    private String nome;

    private String endereco;
    
    private Integer campanhaID;

    public CentralColetaDTO(Integer idCentral, String nome, String endereco, Integer campanhaID) {
        this.idCentral = idCentral;
        this.nome = nome;
        this.endereco = endereco;
        this.campanhaID = campanhaID;
    }

    public Integer getIdCentral() {
        return idCentral;
    }

    public String getNome() {
        return nome;
    }   

    public String getEndereco() {   

        return endereco;
    }

    public Integer getCampanhaID() {
        return campanhaID;
    }

    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }   

    public void setCampanhaID(Integer campanhaID) {
        this.campanhaID = campanhaID;
    }
    
}
