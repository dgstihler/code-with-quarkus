package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Produto;
import com.ismoke.domain.repositories.ProdutoRepository;
import com.ismoke.infrastructure.entities.ProdutoEntity;
import com.ismoke.infrastructure.mappers.ProdutoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepositoryImpl implements ProdutoRepository, PanacheRepositoryBase<ProdutoEntity, String> {

    @Transactional
    @Override
    public void salvar(Produto produto) {
        ProdutoEntity entity = ProdutoMapper.toEntity(produto);
        persist(entity);
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
        return Optional.ofNullable(findById(id))
            .map(ProdutoMapper::toDomain);
    }

    @Transactional
    @Override
    public void deletar(String id) {
        deleteById(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return listAll().stream()
            .map(ProdutoMapper::toDomain)
            .toList();
    }

}
