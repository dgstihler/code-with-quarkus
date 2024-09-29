package com.ismoke.domain.repository;

import com.ismoke.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByKey(String id);

    void save(Product product);

    void update(Product product);

    void delete(String id);

    List<Product> findByEstablishmentId(String establishmentId);

    List<Product> getAllProducts();
}
