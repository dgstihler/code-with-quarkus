package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Venda;
import com.ismoke.domain.repositories.VendaRepository;
import com.ismoke.infrastructure.entities.VendaEntity;
import com.ismoke.infrastructure.mappers.VendaMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VendaRepositoryImpl implements VendaRepository, PanacheRepositoryBase<VendaEntity, String> {

    @Transactional
    @Override
    public void registrarVenda(Venda venda) {
        VendaEntity entity = VendaMapper.toEntity(venda);
        persist(entity);
    }

    @Override
    public Optional<Venda> buscarPorId(String id) {
        return Optional.ofNullable(findById(id))
            .map(VendaMapper::toDomain);
    }

    @Override
    public List<Venda> listarTodas() {
        return listAll().stream()
            .map(VendaMapper::toDomain)
            .toList();
    }
}
