package com.ismoke.application.services;

import com.ismoke.application.dtos.SellerDTO;
import com.ismoke.application.mappers.SellerMapper;
import com.ismoke.domain.models.Seller;
import com.ismoke.domain.repositories.SellerRepository;
import java.util.List;
import java.util.Optional;

public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public SellerDTO criarVendedor(String cnpj, String nome, String email) throws RuntimeException {
        Seller seller = new Seller(cnpj, nome, email);
        sellerRepository.save(seller);
        return SellerMapper.toDTO(seller);
    }

    public List<SellerDTO> listarTodosVendedores() throws RuntimeException {
        return sellerRepository.listAllSellers().stream().map(vendedor ->
                new SellerDTO(
                    vendedor.getCnpj(),
                    vendedor.getName(),
                    vendedor.getEmail()))
            .toList();
    }

    public Optional<SellerDTO> buscarVendedorPorCnpj(String cnpj) throws RuntimeException {
        return sellerRepository.findByCNPJ(cnpj).map(vendedor ->
            new SellerDTO(
                vendedor.getCnpj(),
                vendedor.getName(),
                vendedor.getEmail()));
    }

    public void deletarVendedor(String cnpj) throws RuntimeException {
        sellerRepository.delete(cnpj);
    }
}
