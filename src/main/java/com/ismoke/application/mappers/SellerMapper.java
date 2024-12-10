package com.ismoke.application.mappers;

import com.ismoke.application.dtos.SellerDTO;
import com.ismoke.domain.models.Seller;

public class SellerMapper {

    public static SellerDTO toDTO(Seller comprador) {
        return new SellerDTO(comprador.getCnpj(), comprador.getName(), comprador.getEmail());
    }
}
