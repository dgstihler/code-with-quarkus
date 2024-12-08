package com.ismoke.domain.validations.comprador;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.validations.Validacao;

public class ValidacaoNome implements Validacao<Comprador> {

    @Override
    public void validar(Comprador comprador) {
        if (comprador.getNome() == null
            || comprador.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
    }
}