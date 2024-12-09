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
    public Response criarVendedor(@QueryParam("cnpj") String cnpj,
                                  @QueryParam("nome") String nome,
                                  @QueryParam("email") String email) {
        try {

            VendedorDTO vendedorCriado = vendedorService.criarVendedor(cnpj, nome, email);

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
    public Response deletarVendedor(String cnpj) {
        try {
            vendedorService.deletarVendedor(cnpj);

            return Response.status(Response.Status.NO_CONTENT)
                .entity("Vendedor deletado com sucesso")
                .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
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
        try {
            List<VendedorDTO> compradores = vendedorService.listarTodosVendedores();

            if (compradores.isEmpty()) {
                return Response.ok(compradores).build();
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
