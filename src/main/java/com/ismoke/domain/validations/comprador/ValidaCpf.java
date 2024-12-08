package com.ismoke.domain.validations.comprador;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaCpf implements Validacao<Comprador> {

    @Override
    public void validar(Comprador comprador) {
        if (comprador.getCpf() == null
            || comprador.getCpf().trim().isEmpty()
            || !ValidadorPessoa.cpfValido(comprador.getCpf())) {
            throw new IllegalArgumentException("O CPF esta inválido ou vazio.");
        }
    }
}
