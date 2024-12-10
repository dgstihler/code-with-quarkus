package com.ismoke.test.archunit.injections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ismoke.application.services.CustomerService;
import com.ismoke.application.services.SellerService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class Injections {

    @Inject
    CustomerService customerService;

    @Inject
    SellerService sellerService;

    @Test
    public void deveInjetarCompradorService() {
        assertNotNull(customerService, "CustomerService não foi injetado!");
    }

    @Test
    public void deveInjetarVendedorService() {
        assertNotNull(sellerService, "SellerService não foi injetado!");
    }

}
