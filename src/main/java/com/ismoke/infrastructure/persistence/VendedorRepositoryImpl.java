package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.repositories.VendedorRepository;
import com.ismoke.infrastructure.entities.VendedorEntity;
import com.ismoke.infrastructure.mappers.VendedorMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VendedorRepositoryImpl implements VendedorRepository, PanacheRepositoryBase<VendedorEntity, String> {

    @Override
    public void salvar(Vendedor vendedor) {
        VendedorEntity entity = VendedorMapper.toEntity(vendedor);
        persist(entity);
    }

    @Override
    public Optional<Vendedor> buscarPorCnpj(String id) {
        return Optional.ofNullable(findById(id))
            .map(VendedorMapper::toDomain);
    }

    @Override
    public void deletar(String id) {
        deleteById(id);
    }

    @Override
    public List<Vendedor> listarTodos() {
        return listAll().stream()
            .map(VendedorMapper::toDomain)
            .toList();
    }
}
