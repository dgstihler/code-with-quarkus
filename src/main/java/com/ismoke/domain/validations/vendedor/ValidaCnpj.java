package com.ismoke.domain.validations.vendedor;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaCnpj implements Validacao<Vendedor> {

    @Override
    public void validar(Vendedor vendedor) {
        if (vendedor.getCnpj() == null
            || vendedor.getCnpj().trim().isEmpty()
            || !ValidadorPessoa.CNPJValido(vendedor.getCnpj())) {
            throw new IllegalArgumentException("O CNPJ esta inválido ou vazio.");
        }
    }
}
