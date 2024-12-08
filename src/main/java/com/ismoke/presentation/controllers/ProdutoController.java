package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.ProdutoDTO;
import com.ismoke.application.services.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    private ProdutoService produtoService;

    @POST
    public void cadastrarProduto(@QueryParam("nome") String nome,
                                 @QueryParam("preco") BigDecimal preco,
                                 @QueryParam("quantidade") BigDecimal quantidade) {
        produtoService.criarProduto(nome, preco, quantidade);
    }

    @DELETE
    @Path("/{id}")
    public void deletarProduto(String id) {
        produtoService.deletarProduto(id);
    }

    @GET
    @Path("/{id}")
    public Response buscarProdutoPorId(String id) {
        return produtoService.buscarProdutoPorId(id)
            .map(produto -> Response.ok(produto).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos();

        if (produtos.isEmpty()) {
            return Response.ok(produtos).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
