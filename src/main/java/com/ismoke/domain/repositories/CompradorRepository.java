package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Comprador;
import java.util.List;
import java.util.Optional;

public interface CompradorRepository {
    void salvar(Comprador comprador);

    Optional<Comprador> buscarPorCpf(String cpf);

    List<Comprador> listarTodos();

    void deletar(String cpf);
}
