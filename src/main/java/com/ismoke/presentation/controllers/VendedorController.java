package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.VendedorDTO;
import com.ismoke.application.services.VendedorService;
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

@Path("/vendedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedorController {

    @Inject
    private VendedorService vendedorService;

    @POST
    public void criarVendedor(@QueryParam("cnpj") String cnpj,
                              @QueryParam("nome") String nome,
                              @QueryParam("email") String email) {
        vendedorService.criarVendedor(cnpj, nome, email);
    }

    @DELETE
    @Path("/{cnpj}")
    public void deletarVendedor(String cnpj) {
        vendedorService.deletarVendedor(cnpj);
    }

    @GET
    @Path("/{cnpj}")
    public Response buscarVendedorPorCnpj(String cnpj) {
        VendedorDTO vendedor = vendedorService.buscarVendedorPorCnpj(cnpj)
            .map(v ->
                new VendedorDTO(
                    v.getCnpj(),
                    v.getNome(),
                    v.getEmail()))
            .orElse(null);

        if (vendedor != null) {
            return Response.ok(vendedor).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response listarVendedores() {
        List<VendedorDTO> vendedores = vendedorService.listarTodosVendedores();

        if (vendedores.isEmpty()) {
            return Response.ok(vendedores).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
