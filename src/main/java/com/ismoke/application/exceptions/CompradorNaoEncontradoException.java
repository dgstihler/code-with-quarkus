package com.ismoke.application.exceptions;

public class CompradorNaoEncontradoException extends RuntimeException {
    public CompradorNaoEncontradoException(String cpfComprador) {
        super("Comprador com o cpf" + cpfComprador + " não cadastrado/encontrado.");
    }
}
