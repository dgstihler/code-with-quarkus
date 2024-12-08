package com.ismoke.infrastructure.services;

import com.ismoke.application.services.ProdutoService;
import com.ismoke.domain.repositories.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class ProdutoServiceFactory {
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceFactory(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;

    }

    @Produces
    @ApplicationScoped
    public ProdutoService createProdutoService() {
        return new ProdutoService(produtoRepository);
    }
}
