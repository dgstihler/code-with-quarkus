package com.ismoke.test.archunit.injections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ismoke.application.services.CompradorService;
import com.ismoke.application.services.VendedorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class Injections {

    @Inject
    CompradorService compradorService;

    @Inject
    VendedorService vendedorService;

    @Test
    public void deveInjetarCompradorService() {
        assertNotNull(compradorService, "CompradorService não foi injetado!");
    }

    @Test
    public void deveInjetarVendedorService() {
        assertNotNull(vendedorService, "VendedorService não foi injetado!");
    }

}
