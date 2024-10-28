package com.ismoke.infrastructure.repositories;

import com.ismoke.domain.entities.Establishment;
import com.ismoke.domain.repositories.EstablishmentRepository;
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
