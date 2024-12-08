package com.ismoke.domain.validations.vendedor;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaEmail implements Validacao<Vendedor> {

    @Override
    public void validar(Vendedor vendedor) {
        if (vendedor.getEmail() == null
            || vendedor.getEmail().trim().isEmpty()
            || !ValidadorPessoa.emailValido(vendedor.getEmail())) {
            throw new IllegalArgumentException("O email esta inválido ou vazio.");
        }
    }
}