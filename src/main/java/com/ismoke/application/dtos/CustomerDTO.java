package com.ismoke.application.dtos;

public class CustomerDTO {
    private final String cpf;
    private final String name;
    private final String email;

    public CustomerDTO(String cpf, String name, String email) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
