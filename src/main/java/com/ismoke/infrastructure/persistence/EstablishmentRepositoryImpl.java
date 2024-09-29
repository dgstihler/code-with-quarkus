package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.model.Establishment;
import com.ismoke.domain.repository.EstablishmentRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EstablishmentRepositoryImpl implements EstablishmentRepository, PanacheRepositoryBase<Establishment, String> {

    @Override
    public Optional<Establishment> findByKey(String id) {
        return find("id", id).firstResultOptional();
    }

    @Override
    public void save(Establishment establishment) {
        persist(establishment);
    }

    @Override
    public void update(Establishment establishment) {
        persist(establishment);
    }

    @Override
    public void delete(String id) {
        delete("id", id);
    }

    @Override
    public List<Establishment> getAllEstablishments() {
        return listAll();
    }
}
