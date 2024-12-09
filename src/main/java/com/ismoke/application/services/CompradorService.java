package com.ismoke.application.services;

import com.ismoke.application.dtos.CompradorDTO;
import com.ismoke.application.mappers.CompradorMapper;
import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.repositories.CompradorRepository;
import java.util.List;
import java.util.Optional;

public class CompradorService {

    private final CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public CompradorDTO criarComprador(String cpf, String nome, String email) throws RuntimeException {
        Comprador comprador = new Comprador(cpf, nome, email);
        compradorRepository.salvar(comprador);
        return CompradorMapper.toDTO(comprador);
    }

    public List<CompradorDTO> listarTodosCompradores() throws RuntimeException {
        return compradorRepository.listarTodos().stream().map(comprador ->
                new CompradorDTO(
                    comprador.getCpf(),
                    comprador.getNome(),
                    comprador.getEmail()))
            .toList();
    }

    public Optional<CompradorDTO> buscarCompradorPorCpf(String cpf) throws RuntimeException {
        return compradorRepository.buscarPorCpf(cpf).map(comprador ->
            new CompradorDTO(
                comprador.getCpf(),
                comprador.getNome(),
                comprador.getEmail()));
    }

    public void deletarComprador(String cpf) throws RuntimeException {
        compradorRepository.deletar(cpf);
    }
}


