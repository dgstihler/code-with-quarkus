package com.ismoke.infrastructure.repositories;

import com.ismoke.domain.entities.Product;
import com.ismoke.domain.repositories.ProductRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository, PanacheRepositoryBase<Product, String> {

    @Override
    public Optional<Product> findByKey(String id) {
        return find("id", id).firstResultOptional();
    }

    @Override
    public void save(Product product) {
        persist(product);
    }

    @Override
    public void update(Product product) {
        persist(product);
    }

    @Override
    public void delete(String id) {
        delete("id", id);
    }

    @Override
    public List<Product> findByEstablishmentId(String establishmentId) {
        return list("establishmentId", establishmentId);
    }

    @Override
    public List<Product> getAllProducts() {
        return listAll();
    }
}
