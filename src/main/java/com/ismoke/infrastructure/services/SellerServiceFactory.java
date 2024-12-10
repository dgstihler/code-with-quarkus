package com.ismoke.infrastructure.services;

import com.ismoke.application.services.SellerService;
import com.ismoke.domain.repositories.SellerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class SellerServiceFactory {
    private final SellerRepository sellerRepository;

    public SellerServiceFactory(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Produces
    @ApplicationScoped
    public SellerService createVendedorService() {
        return new SellerService(sellerRepository);
    }
}
