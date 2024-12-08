package com.ismoke.application.dtos;

public class CompradorDTO {
    private final String cpf;
    private final String nome;
    private final String email;

    public CompradorDTO(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
