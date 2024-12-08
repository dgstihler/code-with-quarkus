package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.VendaDTO;
import com.ismoke.application.services.VendaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/vendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendaController {

    @Inject
    private VendaService vendaService;

    @POST
    public Response criarVenda(@QueryParam("cpf_comprador") String cpfComprador,
                               @QueryParam("cnpj_vendedor") String cnpjVendedor,
                               @QueryParam("ids_produtos") List<String> produtos) {
        vendaService.realizarVenda(cpfComprador, cnpjVendedor, produtos);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarVendaPorId(String id) {
        return vendaService.buscarVendaPorId(id)
            .map(venda -> Response.ok(venda)
                .build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response listarVendas() {
        List<VendaDTO> vendas = vendaService.listarTodasVendas();

        if (vendas.isEmpty()) {
            return Response.ok(vendas).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
