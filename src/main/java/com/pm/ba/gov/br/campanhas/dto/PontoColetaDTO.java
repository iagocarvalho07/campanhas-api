package com.pm.ba.gov.br.campanhas.dto;

public class PontoColetaDTO {
    private Integer idPonto;
    private String nome;
    private String endereco;
    private Integer campanhaID;
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
    public Integer getCampanhaID() {
        return campanhaID;
    }
    public void setCampanhaID(Integer campanhaID) {
        this.campanhaID = campanhaID;
    }

    
    
}
