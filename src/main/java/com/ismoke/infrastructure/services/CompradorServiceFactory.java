package com.ismoke.infrastructure.services;

import com.ismoke.application.services.CompradorService;
import com.ismoke.domain.repositories.CompradorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class CompradorServiceFactory {
    private final CompradorRepository compradorRepository;

    public CompradorServiceFactory(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;

    }

    @Produces
    @ApplicationScoped
    public CompradorService createCompradorService() {
        return new CompradorService(compradorRepository);
    }


}


