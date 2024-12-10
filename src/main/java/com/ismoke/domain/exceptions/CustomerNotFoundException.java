package com.ismoke.domain.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String cpf, Throwable cause) {
        super("Customer com CPF '" + cpf + "' não foi encontrado.", cause);
    }
}
