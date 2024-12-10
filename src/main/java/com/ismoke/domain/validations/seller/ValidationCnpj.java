package com.ismoke.domain.validations.seller;

import com.ismoke.domain.models.Seller;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidationCnpj implements Validator<Seller> {

    @Override
    public void validar(Seller seller) {
        if (seller.getCnpj() == null
            || seller.getCnpj().trim().isEmpty()
            || !ValidatorPerson.CNPJValido(seller.getCnpj())) {
            throw new IllegalArgumentException("O CNPJ esta inválido ou vazio.");
        }
    }
}
