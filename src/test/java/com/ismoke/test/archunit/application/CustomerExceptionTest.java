package com.ismoke.test.archunit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.ismoke.domain.exceptions.CustomerNotFoundException;
import com.ismoke.domain.exceptions.CustomersNotFoundException;
import com.ismoke.domain.exceptions.DeleteCustomerException;
import com.ismoke.domain.exceptions.PersistCustomerException;
import org.junit.jupiter.api.Test;

public class CustomerExceptionTest {

    @Test
    void shouldReturnCorrectMessageAndCauseSave() {
        Throwable cause = new RuntimeException("Database is read-only");
        PersistCustomerException exception = new PersistCustomerException("Failed to persist customer", cause);

        assertEquals("Customer não foi possível salvar.", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldReturnCorrectMessageAndCauseDelete() {
        Throwable cause = new RuntimeException("Constraint violation");
        DeleteCustomerException exception = new DeleteCustomerException("12345678900", cause);

        assertEquals("Customer com CPF:12345678900 não foi possível deletar.", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldReturnCorrectMessageWithoutCause() {
        CustomersNotFoundException exception = new CustomersNotFoundException("12345678900");

        assertEquals("Customers not found", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldReturnCorrectMessageWithoutCauseCustomer() {
        Throwable cause = new RuntimeException("Constraint violation");
        CustomerNotFoundException exception = new CustomerNotFoundException("12345678900", cause);

        assertEquals("Customer com CPF '12345678900' não foi encontrado.", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldReturnCorrectMessageWithCause() {
        Throwable cause = new RuntimeException("Database connection failed");
        CustomersNotFoundException exception = new CustomersNotFoundException("12345678900", cause);

        assertEquals("Customers not found", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
