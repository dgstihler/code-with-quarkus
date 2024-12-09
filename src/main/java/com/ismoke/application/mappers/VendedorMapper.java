package com.ismoke.application.mappers;

import com.ismoke.application.dtos.VendedorDTO;
import com.ismoke.domain.models.Vendedor;

public class VendedorMapper {

    public static VendedorDTO toDTO(Vendedor comprador) {
        return new VendedorDTO(comprador.getCnpj(), comprador.getNome(), comprador.getEmail());
    }

    public static Vendedor toDomain(VendedorDTO dto) {
        return new Vendedor(dto.getCnpj(), dto.getNome(), dto.getEmail());
    }
}
