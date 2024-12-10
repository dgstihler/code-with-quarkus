package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.models.Seller;
import com.ismoke.domain.repositories.SellerRepository;
import com.ismoke.infrastructure.entities.SellerEntity;
import com.ismoke.infrastructure.mappers.SellerMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SellerRepositoryImpl implements SellerRepository, PanacheRepositoryBase<SellerEntity, String> {

    @Transactional
    @Override
    public void save(Seller seller) {
        SellerEntity entity = SellerMapper.toEntity(seller);
        persist(entity);
    }

    @Override
    public Optional<Seller> findByCNPJ(String id) {
        return Optional.ofNullable(findById(id))
            .map(SellerMapper::toDomain);
    }

    @Transactional
    @Override
    public void delete(String id) {
        deleteById(id);
    }

    @Override
    public List<Seller> listAllSellers() {
        return listAll().stream()
            .map(SellerMapper::toDomain)
            .toList();
    }
}
