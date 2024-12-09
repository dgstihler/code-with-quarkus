package com.ismoke.application.services;

import com.ismoke.application.dtos.VendedorDTO;
import com.ismoke.application.mappers.VendedorMapper;
import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.repositories.VendedorRepository;
import java.util.List;
import java.util.Optional;

public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorDTO criarVendedor(String cnpj, String nome, String email) throws RuntimeException {
        Vendedor vendedor = new Vendedor(cnpj, nome, email);
        vendedorRepository.salvar(vendedor);
        return VendedorMapper.toDTO(vendedor);
    }

    public List<VendedorDTO> listarTodosVendedores() throws RuntimeException {
        return vendedorRepository.listarTodos().stream().map(vendedor ->
                new VendedorDTO(
                    vendedor.getCnpj(),
                    vendedor.getNome(),
                    vendedor.getEmail()))
            .toList();
    }

    public Optional<VendedorDTO> buscarVendedorPorCnpj(String cnpj) throws RuntimeException {
        return vendedorRepository.buscarPorCnpj(cnpj).map(vendedor ->
            new VendedorDTO(
                vendedor.getCnpj(),
                vendedor.getNome(),
                vendedor.getEmail()));
    }

    public void deletarVendedor(String cnpj) throws RuntimeException {
        vendedorRepository.deletar(cnpj);
    }
}
