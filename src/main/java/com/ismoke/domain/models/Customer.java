package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.customer.ValidationCpf;
import com.ismoke.domain.validations.customer.ValidationEmail;
import com.ismoke.domain.validations.customer.ValidateNameWithoutProfanity;
import com.ismoke.domain.validations.customer.ValidationName;
import java.util.List;

public class Customer {
    private final String cpf;
    private final String name;
    private final String email;

    public Customer(String cpf, String name, String email) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;

        List<Validator<Customer>> validacoes = List.of(
            new ValidationCpf(),
            new ValidationName(),
            new ValidationEmail(),
            new ValidateNameWithoutProfanity()
        );

        Validador<Customer> validadorComprador = new Validador<>(validacoes);
        validadorComprador.validar(this);
    }

    // Getters
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
