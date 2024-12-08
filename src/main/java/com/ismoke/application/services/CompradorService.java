package com.ismoke.application.services;

import com.ismoke.application.dtos.CompradorDTO;
import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.repositories.CompradorRepository;
import java.util.List;
import java.util.Optional;

public class CompradorService {

    private final CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public void criarComprador(String cpf, String nome, String email) {
        Comprador comprador = new Comprador(cpf, nome, email);
        compradorRepository.salvar(comprador);
    }

    public List<CompradorDTO> listarTodosCompradores() {
        return compradorRepository.listarTodos().stream().map(comprador ->
                new CompradorDTO(
                    comprador.getCpf(),
                    comprador.getNome(),
                    comprador.getEmail()))
            .toList();
    }

    public Optional<CompradorDTO> buscarCompradorPorCpf(String cpf) {
        return compradorRepository.buscarPorCpf(cpf).map(comprador ->
            new CompradorDTO(
                comprador.getCpf(),
                comprador.getNome(),
                comprador.getEmail()));
    }

    public void deletarComprador(String cpf) {
        compradorRepository.deletar(cpf);
    }
}


