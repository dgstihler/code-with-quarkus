package com.ismoke.domain.validations;

public interface Validator<T> {
    void validar(T entidade);
}
