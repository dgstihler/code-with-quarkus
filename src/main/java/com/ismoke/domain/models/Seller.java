package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.seller.ValidationCnpj;
import com.ismoke.domain.validations.seller.ValidationEmail;
import com.ismoke.domain.validations.seller.ValidateNameWithoutProfanity;
import com.ismoke.domain.validations.seller.ValidationNome;
import java.util.List;

public class Seller {
    private final String cnpj;
    private final String name;
    private final String email;

    public Seller(String cnpj, String name, String email) {
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;

        List<Validator<Seller>> validacoes = List.of(
            new ValidationNome(),
            new ValidationCnpj(),
            new ValidationEmail(),
            new ValidateNameWithoutProfanity()
        );

        Validador<Seller> validadorVendedor = new Validador<>(validacoes);
        validadorVendedor.validar(this);
    }

    // Getters
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
