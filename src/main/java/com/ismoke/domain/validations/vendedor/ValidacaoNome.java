package com.ismoke.domain.validations.vendedor;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.validations.Validacao;

public class ValidacaoNome implements Validacao<Vendedor> {

    @Override
    public void validar(Vendedor vendedor) {
        if (vendedor.getNome() == null
            || vendedor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O nome é obrigatório."
            );
        }
    }
}