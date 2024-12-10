package com.ismoke.domain.validations.customer;

import com.ismoke.domain.models.Customer;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidateNameWithoutProfanity implements Validator<Customer> {

    @Override
    public void validar(Customer customer) {
        if (!ValidatorPerson.validarNomeSemPalavroes(customer.getName())) {
            throw new IllegalArgumentException("O nome contém palavras impróprias.");
        }
    }
}