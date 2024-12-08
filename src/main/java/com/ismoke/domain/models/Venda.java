package com.ismoke.domain.models;

import com.ismoke.domain.validations.Validacao;
import com.ismoke.domain.validations.Validador;
import com.ismoke.domain.validations.venda.ValidacaListaVazia;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private final BigDecimal id;
    private final Comprador comprador;
    private final Vendedor vendedor;
    private final List<Produto> produtos;
    private final BigDecimal valorTotal;
    private final LocalDateTime dataVenda;

    public Venda(BigDecimal id, Comprador comprador, Vendedor vendedor, List<Produto> produtos) {
        this.id = id;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produtos = produtos;

        List<Validacao<Venda>> validacoes = List.of(new ValidacaListaVazia());
        Validador<Venda> validadorVenda = new Validador<>(validacoes);
        validadorVenda.validar(this);

        this.valorTotal = produtos.stream()
            .map(produto -> produto.getPreco().multiply(produto.getQuantidade()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.dataVenda = LocalDateTime.now();
    }

    // Getters
    public BigDecimal getId() {
        return id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }
}
