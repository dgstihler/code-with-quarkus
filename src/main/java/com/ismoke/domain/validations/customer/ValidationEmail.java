package com.ismoke.domain.validations.customer;

import com.ismoke.domain.models.Customer;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidationEmail implements Validator<Customer> {

    @Override
    public void validar(Customer customer) {
        if (customer.getEmail() == null
            || customer.getEmail().trim().isEmpty()
            || !ValidatorPerson.emailValido(customer.getEmail())) {
            throw new IllegalArgumentException("O email esta inválido ou vazio.");
        }
    }
}