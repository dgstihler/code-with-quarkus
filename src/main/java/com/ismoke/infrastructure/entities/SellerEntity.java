package com.ismoke.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class SellerEntity {

    @Id
    private String cnpj;

    private String nome;

    private String email;

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
}
