package com.ismoke.domain.exceptions;

public class DeleteCustomerException extends RuntimeException {
    public DeleteCustomerException(String cpf, Throwable cause) {
        super("Customer com CPF:" + cpf + " não foi possível deletar.", cause);
    }
}
