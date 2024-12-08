package com.ismoke.application.dtos;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.models.Produto;
import com.ismoke.domain.models.Vendedor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VendaDTO {
    private final BigDecimal id;
    private final Comprador comprador;
    private final Vendedor vendedor;
    private final List<Produto> produtos;
    private BigDecimal valorTotal;
    private LocalDateTime dataVenda;

    public VendaDTO(Vendedor vendedor, List<Produto> produtos, Comprador comprador, BigDecimal id) {
        this.vendedor = vendedor;
        this.produtos = produtos;
        this.comprador = comprador;
        this.id = id;
    }

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

