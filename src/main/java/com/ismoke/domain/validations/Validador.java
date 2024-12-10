package com.ismoke.domain.validations;

import java.util.List;

public class Validador<T> {

    private final List<Validator<T>> validacoes;

    public Validador(List<Validator<T>> validacoes) {
        this.validacoes = validacoes;
    }

    public void validar(T entidade) {
        for (Validator<T> validator : validacoes) {
            validator.validar(entidade);
        }
    }
}


