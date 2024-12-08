package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.produtos.ValidacaoId;
import com.ismoke.domain.validations.produtos.ValidacaoPrecoMinimo;
import java.math.BigDecimal;
import java.util.List;

public class Produto {
    private final String id;
    private final String nome;
    private final BigDecimal preco;
    private final BigDecimal quantidade;

    public Produto(String id, String nome, BigDecimal preco, BigDecimal quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;

        List<Validacao<Produto>> validacoes = List.of(
            new ValidacaoId(),
            new ValidacaoPrecoMinimo()
        );

        Validador<Produto> validadorProduto = new Validador<>(validacoes);
        validadorProduto.validar(this);
    }

    // Getters
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

