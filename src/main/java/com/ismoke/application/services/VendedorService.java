package com.ismoke.application.services;

import com.ismoke.application.dtos.VendedorDTO;
import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.repositories.VendedorRepository;
import java.util.List;
import java.util.Optional;

public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public void criarVendedor(String cnpj, String nome, String email) {
        Vendedor vendedor = new Vendedor(cnpj, nome, email);
        vendedorRepository.salvar(vendedor);
    }

    public List<VendedorDTO> listarTodosVendedores() {
        return vendedorRepository.listarTodos().stream().map(vendedor ->
                new VendedorDTO(
                    vendedor.getCnpj(),
                    vendedor.getNome(),
                    vendedor.getEmail()))
            .toList();
    }

    public Optional<VendedorDTO> buscarVendedorPorCnpj(String cnpj) {
        return vendedorRepository.buscarPorCnpj(cnpj).map(vendedor ->
            new VendedorDTO(
                vendedor.getCnpj(),
                vendedor.getNome(),
                vendedor.getEmail()));
    }

    public void deletarVendedor(String cnpj) {
        vendedorRepository.deletar(cnpj);
    }
}
