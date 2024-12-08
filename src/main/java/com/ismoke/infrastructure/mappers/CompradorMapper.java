package com.ismoke.infrastructure.mappers;

import com.ismoke.domain.models.Comprador;
import com.ismoke.infrastructure.entities.CompradorEntity;

public class CompradorMapper {

    public static CompradorEntity toEntity(Comprador comprador) {
        CompradorEntity entity = new CompradorEntity();
        entity.setCpf(comprador.getCpf());
        entity.setNome(comprador.getNome());
        entity.setEmail(comprador.getEmail());
        return entity;
    }

    public static Comprador toDomain(CompradorEntity entity) {
        return new Comprador(
            entity.getCpf(),
            entity.getNome(),
            entity.getEmail()
        );
    }
}
