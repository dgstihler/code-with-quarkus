package com.ismoke.interfaces;

import com.ismoke.application.dtos.UserDTO;
import com.ismoke.application.services.UserApplicationService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserApplicationService userApplicationService;

    @GET
    public List<UserDTO> getAllUsers() {
        System.out.println("teste chamada");
        return userApplicationService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        Optional<UserDTO> user = userApplicationService.findByKey(id);

        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        }

        JsonObject errorMessage = Json.createObjectBuilder()
            .add("error", "User not found")
            .add("message", "The user with ID " + id + " was not found.")
            .build();

        return Response.status(Response.Status.NOT_FOUND)
            .entity(errorMessage)
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, UserDTO updatedUser) {
        Optional<UserDTO> user = userApplicationService.findByKey(id);

        if (user.isPresent()) {
            userApplicationService.update(user.get());
            return Response.ok(updatedUser).build();
        }
        JsonObject errorMessage = Json.createObjectBuilder()
            .add("error", "User not found")
            .add("message", "The user with ID " + id + " was not found to update .")
            .build();

        return Response.status(Response.Status.NOT_FOUND)
            .entity(errorMessage)
            .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            userApplicationService.deleteUser(id);
        } catch (RuntimeException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Response.Status.ACCEPTED).build();

    }
}