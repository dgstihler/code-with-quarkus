package com.ismoke.domain.validations.comprador;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaNomeSemPalavroes implements Validacao<Comprador> {

    @Override
    public void validar(Comprador comprador) {
        if (!ValidadorPessoa.validarNomeSemPalavroes(comprador.getNome())) {
            throw new IllegalArgumentException("O nome contém palavras impróprias.");
        }
    }
}