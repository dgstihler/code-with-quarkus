package com.ismoke.domain.validations.customer;

import com.ismoke.domain.models.Customer;
import com.ismoke.domain.validations.Validator;

public class ValidationName implements Validator<Customer> {

    @Override
    public void validar(Customer customer) {
        if (customer.getName() == null
            || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
    }
}