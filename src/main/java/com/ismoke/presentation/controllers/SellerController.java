package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.SellerDTO;
import com.ismoke.application.services.SellerService;
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
import java.util.List;

@Path("/sellers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SellerController {

    @Inject
    private SellerService sellerService;

    @POST
    public Response createSeller(@QueryParam("cnpj") String cnpj,
                                  @QueryParam("name") String nome,
                                  @QueryParam("email") String email) {
        try {

            SellerDTO vendedorCriado = sellerService.criarVendedor(cnpj, nome, email);

            return Response.status(Response.Status.CREATED)
                .entity(vendedorCriado)
                .build();
        } catch (RuntimeException e) {

            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{cnpj}")
    public Response deleteSeller(String cnpj) {
        try {
            sellerService.deletarVendedor(cnpj);

            return Response.status(Response.Status.NO_CONTENT)
                .entity("Seller deletado com sucesso")
                .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{cnpj}")
    public Response findByCNPJ(String cnpj) {
        SellerDTO vendedor = sellerService.buscarVendedorPorCnpj(cnpj)
            .map(v ->
                new SellerDTO(
                    v.getCnpj(),
                    v.getName(),
                    v.getEmail()))
            .orElse(null);

        if (vendedor != null) {
            return Response.ok(vendedor).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response listAllSellers() {
        try {
            List<SellerDTO> vendedores = sellerService.listarTodosVendedores();

            if (!vendedores.isEmpty()) {
                return Response.ok(vendedores).build();
            }
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
            .entity("Nenhum vendedor a ser listado")
            .build();
    }
}
