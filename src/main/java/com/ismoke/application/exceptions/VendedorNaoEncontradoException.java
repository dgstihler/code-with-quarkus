package com.ismoke.application.exceptions;

public class VendedorNaoEncontradoException extends RuntimeException {
    public VendedorNaoEncontradoException(String cnpjVendor) {
        super("Vendedor com o cnpj" + cnpjVendor + " não cadastrado/encontrado.");
    }
}
