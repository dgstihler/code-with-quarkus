package com.ismoke.presentation.controllers;

import com.ismoke.domain.model.Product;
import com.ismoke.infrastructure.persistence.ProductRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductRepositoryImpl productRepository;

    @GET
    public List<Product> getAllProducts() {
        return productRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") String id) {
        Optional<Product> product = productRepository.findByKey(id);
        if (product.isPresent()) {
            return Response.ok(product.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findByKey(id);
        if (existingProduct.isPresent()) {
            productRepository.update(updatedProduct);
            return Response.ok(updatedProduct).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
