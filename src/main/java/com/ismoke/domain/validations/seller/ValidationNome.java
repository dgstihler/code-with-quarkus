package com.ismoke.domain.validations.seller;

import com.ismoke.domain.models.Seller;
import com.ismoke.domain.validations.Validator;

public class ValidationNome implements Validator<Seller> {

    @Override
    public void validar(Seller seller) {
        if (seller.getName() == null
            || seller.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nome é obrigatório."
            );
        }
    }
}