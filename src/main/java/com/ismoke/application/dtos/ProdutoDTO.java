package com.ismoke.application.dtos;

import java.math.BigDecimal;

public class ProdutoDTO {
    private final String id;
    private final String nome;
    private final BigDecimal preco;
    private final BigDecimal quantidade;

    public ProdutoDTO(String id, String nome, BigDecimal preco, BigDecimal quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }
}
