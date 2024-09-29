package com.ismoke.presentation.controllers;

import com.ismoke.domain.model.Establishment;
import com.ismoke.infrastructure.persistence.EstablishmentRepositoryImpl;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/establishment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstablishmentController {

    @Inject
    EstablishmentRepositoryImpl establishmentRepository;

    @GET
    public List<Establishment> getAllEstablishments() {
        return establishmentRepository.getAllEstablishments();
    }

    @GET
    @Path("/{id}")
    public Response getEstablishmentById(@PathParam("id") String id) {
        Optional<Establishment> establishment = establishmentRepository.findByKey(id);
        if (establishment.isPresent()) {
            return Response.ok(establishment.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEstablishment(@PathParam("id") String id, Establishment updatedEstablishment) {
        Optional<Establishment> existingEstablishment = establishmentRepository.findByKey(id);
        if (existingEstablishment.isPresent()) {
            establishmentRepository.update(updatedEstablishment);
            return Response.ok(updatedEstablishment).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
