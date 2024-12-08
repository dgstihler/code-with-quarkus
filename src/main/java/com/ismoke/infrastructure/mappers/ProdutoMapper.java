package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Produto;
import com.ismoke.infrastructure.entities.ProdutoEntity;

public class ProdutoMapper {

    public static ProdutoEntity toEntity(Produto Produto) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(Produto.getId());
        entity.setNome(Produto.getNome());
        entity.setPreco(Produto.getPreco());
        entity.setQuantidade(Produto.getQuantidade());
        return entity;
    }

    public static Produto toDomain(ProdutoEntity entity) {
        return new Produto(
            entity.getId(),
            entity.getNome(),
            entity.getPreco(),
            entity.getQuantidade()
        );
    }
}
