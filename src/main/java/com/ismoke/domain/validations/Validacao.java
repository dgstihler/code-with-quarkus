package com.ismoke.domain.validations;

public interface Validacao<T> {
    void validar(T entidade);
}
