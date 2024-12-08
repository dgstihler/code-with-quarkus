package com.ismoke.infrastructure.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendas")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @ManyToOne
    @JoinColumn(name = "comprador_cpf", nullable = false)
    private CompradorEntity comprador;

    @ManyToOne
    @JoinColumn(name = "vendedor_cnpj", nullable = false)
    private VendedorEntity vendedor;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoEntity> produtos;

    private int quantidade;

    private BigDecimal valorTotal;

    private LocalDateTime dataVenda;

    // Getters e setters
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public CompradorEntity getComprador() {
        return comprador;
    }

    public void setComprador(CompradorEntity comprador) {
        this.comprador = comprador;
    }

    public VendedorEntity getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
    }

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}
