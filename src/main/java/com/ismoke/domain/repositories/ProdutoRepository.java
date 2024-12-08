package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    void salvar(Produto produto);

    Optional<Produto> buscarPorId(String id);

    List<Produto> listarTodos();

    void deletar(String id);
}
