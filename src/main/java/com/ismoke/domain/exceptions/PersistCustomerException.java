package com.ismoke.domain.exceptions;

public class PersistCustomerException extends RuntimeException {
    public PersistCustomerException(String message, Throwable cause) {
        super("Customer não foi possível salvar.", cause);
    }
}