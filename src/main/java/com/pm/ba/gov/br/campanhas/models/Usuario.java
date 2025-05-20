package com.pm.ba.gov.br.campanhas.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "senha", nullable = false)
    private String senha;
    
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    
    @ManyToMany
    @JoinTable(
        name = "usuario_ponto",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_ponto")
    )
    private Set<PontoColeta> pontosColeta;
    
    @ManyToMany
    @JoinTable(
        name = "usuario_central",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_central")
    )
    private Set<CentralColeta> centraisColeta;

    // Getters e Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<PontoColeta> getPontosColeta() {
        return pontosColeta;
    }

    public void setPontosColeta(Set<PontoColeta> pontosColeta) {
        this.pontosColeta = pontosColeta;
    }

    public Set<CentralColeta> getCentraisColeta() {
        return centraisColeta;
    }

    public void setCentraisColeta(Set<CentralColeta> centraisColeta) {
        this.centraisColeta = centraisColeta;
    }
} 