package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Vendedor;
import java.util.List;
import java.util.Optional;

public interface VendedorRepository {

    void salvar(Vendedor vendedor);

    Optional<Vendedor> buscarPorCnpj(String cnpj);

    List<Vendedor> listarTodos();

    void deletar(String cnpj);
}
