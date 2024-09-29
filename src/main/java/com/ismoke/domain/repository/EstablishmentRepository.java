package com.ismoke.domain.repository;

import com.ismoke.domain.model.Establishment;
import java.util.List;
import java.util.Optional;

public interface EstablishmentRepository {
    Optional<Establishment> findByKey(String id);

    void save(Establishment establishment);

    void update(Establishment establishment);

    void delete(String id);

    List<Establishment> getAllEstablishments();
}
