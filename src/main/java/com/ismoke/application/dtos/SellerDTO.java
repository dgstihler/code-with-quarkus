package com.ismoke.application.dtos;

public class SellerDTO {
    private final String cnpj;
    private final String name;
    private final String email;

    public SellerDTO(String cnpj, String name, String email) {
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
