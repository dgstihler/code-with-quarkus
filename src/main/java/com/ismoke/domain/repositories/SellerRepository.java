package com.ismoke.domain.repositories;

import com.ismoke.domain.models.Seller;
import java.util.List;
import java.util.Optional;

public interface SellerRepository {

    void save(Seller seller);

    Optional<Seller> findByCNPJ(String cnpj);

    List<Seller> listAllSellers();

    void delete(String cnpj);
}
