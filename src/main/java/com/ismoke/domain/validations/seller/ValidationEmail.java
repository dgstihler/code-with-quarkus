package com.ismoke.domain.validations.seller;

import com.ismoke.domain.models.Seller;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidationEmail implements Validator<Seller> {

    @Override
    public void validar(Seller seller) {
        if (seller.getEmail() == null
            || seller.getEmail().trim().isEmpty()
            || !ValidatorPerson.emailValido(seller.getEmail())) {
            throw new IllegalArgumentException("O email esta inválido ou vazio.");
        }
    }
}