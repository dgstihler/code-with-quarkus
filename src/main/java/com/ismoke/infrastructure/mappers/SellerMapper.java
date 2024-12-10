package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Seller;
import com.ismoke.infrastructure.entities.SellerEntity;

public class SellerMapper {

    public static SellerEntity toEntity(Seller Seller) {
        SellerEntity entity = new SellerEntity();
        entity.setCnpj(Seller.getCnpj());
        entity.setNome(Seller.getName());
        entity.setEmail(Seller.getEmail());
        return entity;
    }

    public static Seller toDomain(SellerEntity entity) {
        return new Seller(
            entity.getCnpj(),
            entity.getNome(),
            entity.getEmail()
        );
    }
}
