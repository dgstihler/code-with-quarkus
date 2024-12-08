package com.ismoke.domain.validations.produtos;

import com.ismoke.domain.models.Produto;
import com.ismoke.domain.validations.Validacao;
import java.math.BigDecimal;

public class ValidacaoPrecoMinimo implements Validacao<Produto> {

    private final BigDecimal precoMinimo = BigDecimal.ZERO;

    @Override
    public void validar(Produto produto) {
        if (produto.getPreco().compareTo(precoMinimo) <= 0) {
            throw new IllegalArgumentException(
                "O preço deve ser maior que zero."
            );
        }
    }
}