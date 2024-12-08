package com.ismoke.infrastructure.services;

import com.ismoke.application.services.VendaService;
import com.ismoke.domain.repositories.CompradorRepository;
import com.ismoke.domain.repositories.ProdutoRepository;
import com.ismoke.domain.repositories.VendaRepository;
import com.ismoke.domain.repositories.VendedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class VendaServiceFactory {
    private final VendaRepository vendaRepository;
    private final CompradorRepository compradorRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;

    public VendaServiceFactory(VendaRepository vendaRepository,
                               CompradorRepository compradorRepository,
                               VendedorRepository vendedorRepository,
                               ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.compradorRepository = compradorRepository;
        this.vendedorRepository = vendedorRepository;
        this.produtoRepository = produtoRepository;
    }

    @Produces
    @ApplicationScoped
    public VendaService createVendaService() {
        return new VendaService(vendaRepository, compradorRepository, vendedorRepository, produtoRepository);
    }
}
