package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaRepository {
    void registrarVenda(Venda venda);

    Optional<Venda> buscarPorId(String id);

    List<Venda> listarTodas();
}
