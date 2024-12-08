package com.ismoke.domain.validations;

import java.util.List;

public class Validador<T> {

    private final List<Validacao<T>> validacoes;

    public Validador(List<Validacao<T>> validacoes) {
        this.validacoes = validacoes;
    }

    public void validar(T entidade) {
        for (Validacao<T> validacao : validacoes) {
            validacao.validar(entidade);
        }
    }
}


