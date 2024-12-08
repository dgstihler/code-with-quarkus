package com.ismoke.application.dtos;

public class VendedorDTO {
    private final String cnpj;
    private final String nome;
    private final String email;

    public VendedorDTO(String cnpj, String nome, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
