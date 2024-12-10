package com.ismoke.domain.validations.seller;

import com.ismoke.domain.models.Seller;
import com.ismoke.domain.validations.Validator;
import com.ismoke.domain.validations.shared.ValidatorPerson;

public class ValidateNameWithoutProfanity implements Validator<Seller> {

    @Override
    public void validar(Seller seller) {
        if (!ValidatorPerson.validarNomeSemPalavroes(seller.getName())) {
            throw new IllegalArgumentException("O nome contém palavras impróprias.");
        }
    }
}