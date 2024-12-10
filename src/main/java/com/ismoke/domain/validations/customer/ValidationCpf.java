package com.ismoke.domain.validations.customer;

import com.ismoke.domain.models.Customer;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidationCpf implements Validator<Customer> {

    @Override
    public void validar(Customer customer) {
        if (customer.getCpf() == null
            || customer.getCpf().trim().isEmpty()
            || !ValidatorPerson.cpfValido(customer.getCpf())) {
            throw new IllegalArgumentException("O CPF esta inválido ou vazio.");
        }
    }
}
