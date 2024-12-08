package com.ismoke.domain.validations.produtos;

import com.ismoke.domain.models.Produto;
import com.ismoke.domain.validations.Validacao;
import java.util.Optional;

public class ValidacaoId implements Validacao<Produto> {

    @Override
    public void validar(Produto produto) {
        if (Optional.ofNullable(produto.getId()).isEmpty()) {
            throw new IllegalArgumentException(
                "O ID do produto é obrigatório."
            );
        }
    }
}