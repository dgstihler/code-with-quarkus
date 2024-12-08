package com.ismoke.infrastructure.services;

import com.ismoke.application.services.VendedorService;
import com.ismoke.domain.repositories.VendedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class VendedorServiceFactory  {
    private final VendedorRepository vendedorRepository;

    public VendedorServiceFactory(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Produces
    @ApplicationScoped
    public VendedorService createVendedorService() {
        return new VendedorService(vendedorRepository);
    }
}
