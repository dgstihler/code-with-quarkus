package com.ismoke.domain.validations.comprador;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaEmail implements Validacao<Comprador> {

    @Override
    public void validar(Comprador comprador) {
        if (comprador.getEmail() == null
            || comprador.getEmail().trim().isEmpty()
            || !ValidadorPessoa.emailValido(comprador.getEmail())) {
            throw new IllegalArgumentException("O email esta inválido ou vazio.");
        }
    }
}