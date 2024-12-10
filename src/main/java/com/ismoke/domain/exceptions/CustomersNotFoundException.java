package com.ismoke.domain.exceptions;

public class CustomersNotFoundException extends RuntimeException {
    public CustomersNotFoundException(String cpf, Throwable cause) {
        super("Customers not found", cause);
    }

    public CustomersNotFoundException(String cpf) {
        super("Customers not found");
    }
}
