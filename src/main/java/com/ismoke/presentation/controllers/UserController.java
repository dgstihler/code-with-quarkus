package com.ismoke.presentation.controllers;

import com.ismoke.domain.model.User;
import com.ismoke.infrastructure.persistence.UserRepositoryImpl;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserRepositoryImpl userRepository;

    @GET
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        Optional<User> user = userRepository.findByKey(id);
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User updatedUser) {
        Optional<User> existingUser = userRepository.findByKey(id);
        if (existingUser.isPresent()) {
            userRepository.update(updatedUser);
            return Response.ok(updatedUser).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}