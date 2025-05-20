package com.pm.ba.gov.br.campanhas.models;


public class UserModel {
    private String email;
    private String matricula;
    private String password;
    private String role;
    private boolean isActive;
    
    public UserModel(String email, String password, String role, boolean isActive) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
