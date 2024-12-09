package com.ismoke.application.mappers;

import com.ismoke.application.dtos.CompradorDTO;
import com.ismoke.domain.models.Comprador;
import com.ismoke.infrastructure.entities.CompradorEntity;

public class CompradorMapper {

    public static CompradorDTO toDTO(Comprador comprador) {
        return new CompradorDTO(comprador.getCpf(), comprador.getNome(), comprador.getEmail());
    }

    public static Comprador toDomain(CompradorDTO dto) {
        return new Comprador(dto.getCpf(), dto.getNome(), dto.getEmail());
    }
}
