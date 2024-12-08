package com.ismoke.domain.validations.venda;

import com.ismoke.domain.models.Produto;
import com.ismoke.domain.models.Venda;
import com.ismoke.domain.validations.Validacao;
import java.util.Optional;

public class ValidacaListaVazia implements Validacao<Venda> {

    @Override
    public void validar(Venda venda) {
        if (venda.getProdutos() == null || venda.getProdutos().isEmpty()) {
            throw new IllegalArgumentException(
                "A lista de produtos não pode ser vazia."
            );
        }
    }
}