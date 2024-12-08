package com.ismoke.domain.validations.vendedor;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.shared.ValidadorPessoa;

public class ValidaNomeSemPalavroes implements Validacao<Vendedor> {

    @Override
    public void validar(Vendedor vendedor) {
        if (!vendedor.getNome().trim().isEmpty()
            && !ValidadorPessoa.validarNomeSemPalavroes(vendedor.getNome())) {
            throw new IllegalArgumentException("O nome contém palavras impróprias.");
        }
    }
}