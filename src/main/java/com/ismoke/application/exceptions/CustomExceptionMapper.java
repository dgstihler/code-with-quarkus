package com.ismoke.application.exceptions;

import com.ismoke.domain.exceptions.CustomerNotFoundException;
import com.ismoke.domain.exceptions.CustomersNotFoundException;
import com.ismoke.domain.exceptions.DeleteCustomerException;
import com.ismoke.domain.exceptions.PersistCustomerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        if (exception instanceof CustomerNotFoundException || exception instanceof CustomersNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
        }
        if (exception instanceof DeleteCustomerException) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
        }
        if (exception instanceof PersistCustomerException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage())
                .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Erro inesperado: " + exception.getMessage())
            .build();
    }
}
