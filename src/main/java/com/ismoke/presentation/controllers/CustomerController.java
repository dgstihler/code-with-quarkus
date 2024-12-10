package com.ismoke.presentation.controllers;

import com.ismoke.application.dtos.CustomerDTO;
import com.ismoke.application.services.CustomerService;
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

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    public Response createCustomer(@QueryParam("cpf") String cpf,
                                   @QueryParam("name") String nome,
                                   @QueryParam("email") String email) {

        CustomerDTO createdCustomer = customerService.createCustomer(cpf, nome, email);

        return Response.status(Response.Status.CREATED)
            .entity(createdCustomer)
            .build();
    }

    @DELETE
    @Path("/{cpf}")
    public Response delete(@PathParam("cpf") String cpf) {

        customerService.delete(cpf);

        return Response.status(Response.Status.OK)
            .entity("Customer deletado com sucesso")
            .build();
    }

    @GET
    @Path("/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf) {
        return customerService.findByCpf(cpf)
            .map(customer -> Response.ok(customer).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response listAllCustomers() {

        List<CustomerDTO> customers = customerService.listAllCustomers();

        return Response.ok(customers).build();
    }
}
