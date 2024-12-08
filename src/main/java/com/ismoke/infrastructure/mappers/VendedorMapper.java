package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.infrastructure.entities.VendedorEntity;

public class VendedorMapper {

    public static VendedorEntity toEntity(Vendedor Vendedor) {
        VendedorEntity entity = new VendedorEntity();
        entity.setCnpj(Vendedor.getCnpj());
        entity.setNome(Vendedor.getNome());
        entity.setEmail(Vendedor.getEmail());
        return entity;
    }

    public static Vendedor toDomain(VendedorEntity entity) {
        return new Vendedor(
            entity.getCnpj(),
            entity.getNome(),
            entity.getEmail()
        );
    }
}
