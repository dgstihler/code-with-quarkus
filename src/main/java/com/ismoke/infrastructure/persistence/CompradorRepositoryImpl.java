package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.repositories.CompradorRepository;
import com.ismoke.infrastructure.entities.CompradorEntity;
import com.ismoke.infrastructure.mappers.CompradorMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CompradorRepositoryImpl implements CompradorRepository, PanacheRepositoryBase<CompradorEntity, String> {

    @Transactional
    @Override
    public void salvar(Comprador comprador) {
        CompradorEntity entity = CompradorMapper.toEntity(comprador);
        persist(entity);
    }

    @Override
    public Optional<Comprador> buscarPorCpf(String cpf) {
        return Optional.ofNullable(findById(cpf))
            .map(CompradorMapper::toDomain);
    }

    @Transactional
    @Override
    public void deletar(String cpf) {
        deleteById(cpf);
    }

    @Override
    public List<Comprador> listarTodos() {
        return listAll().stream()
            .map(CompradorMapper::toDomain)
            .toList();
    }
}
