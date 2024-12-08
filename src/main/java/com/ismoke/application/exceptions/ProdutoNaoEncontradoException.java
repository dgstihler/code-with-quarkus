package com.ismoke.application.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String produtoId) {
        super("Produto não encontrado para o ID: " + produtoId);
    }
}
