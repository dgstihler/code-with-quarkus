package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.CompradorDTO;
import com.ismoke.application.services.CompradorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.util.List;

import jakarta.ws.rs.core.MediaType;

@Path("/compradores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorController {

    @Inject
    CompradorService compradorService;

    @POST
    public Response criarComprador(@QueryParam("cpf") String cpf,
                                   @QueryParam("nome") String nome,
                                   @QueryParam("email") String email) {
        try {

            CompradorDTO compradorCriado = compradorService.criarComprador(cpf, nome, email);

            return Response.status(Response.Status.CREATED)
                .entity(compradorCriado)
                .build();
        } catch (RuntimeException e) {

            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    public Response deletarComprador(@PathParam("cpf") String cpf) {
        try {
            compradorService.deletarComprador(cpf);

            return Response.status(Response.Status.NO_CONTENT)
                .entity("Comprador deletado com sucesso")
                .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{cpf}")
    public Response buscarCompradorPorId(@PathParam("cpf") String cpf) {
        return compradorService.buscarCompradorPorCpf(cpf)
            .map(comprador -> Response.ok(comprador).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response listarCompradores() {
        try {
            List<CompradorDTO> compradores = compradorService.listarTodosCompradores();

            if (compradores.isEmpty()) {
                return Response.ok(compradores).build();
            }
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
            .entity("Nenhum comprador a ser listado")
            .build();
    }
}
