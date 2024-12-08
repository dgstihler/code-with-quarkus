package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.models.Produto;
import com.ismoke.domain.models.Venda;
import com.ismoke.domain.models.Vendedor;
import com.ismoke.infrastructure.entities.CompradorEntity;
import com.ismoke.infrastructure.entities.VendaEntity;
import java.util.List;
import java.util.stream.Collectors;

public class VendaMapper {

    public static VendaEntity toEntity(Venda venda) {
        VendaEntity entity = new VendaEntity();
        entity.setComprador(CompradorMapper.toEntity(venda.getComprador()));
        entity.setVendedor(VendedorMapper.toEntity(venda.getVendedor()));
        entity.setProdutos(venda.getProdutos().stream().map(ProdutoMapper::toEntity).collect(Collectors.toList()));
        return entity;
    }

    public static Venda toDomain(VendaEntity entity) {
        Comprador comprador = new Comprador(
            entity.getComprador().getCpf(),
            entity.getComprador().getNome(),
            entity.getComprador().getEmail()
        );
        Vendedor vendedor = new Vendedor(
            entity.getVendedor().getCnpj(),
            entity.getVendedor().getNome(),
            entity.getVendedor().getEmail()
        );

        List<Produto> produtos = entity.getProdutos().stream().map(produto -> new Produto(
            produto.getId(),
            produto.getNome(),
            produto.getPreco(),
            produto.getQuantidade()
        )).collect(Collectors.toList());

        return new Venda(
            entity.getId(),
            comprador,
            vendedor,
            produtos
        );
    }
}
