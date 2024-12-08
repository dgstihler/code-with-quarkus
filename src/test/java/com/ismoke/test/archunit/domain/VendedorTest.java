package com.ismoke.test.archunit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ismoke.domain.models.Vendedor;
import org.junit.jupiter.api.Test;

class VendedorTest {

    @Test
    void deveCriarVendedorComCNPJValido() {
        Vendedor vendedor = new Vendedor("12.345.678/0001-95", "Empresa A", "empresa@gmail.com");
        assertNotNull(vendedor);
        assertEquals("12.345.678/0001-95", vendedor.getCnpj());
        assertEquals("Empresa A", vendedor.getNome());
        assertEquals("empresa@gmail.com", vendedor.getEmail());
    }

    @Test
    void deveLancarExcecaoParaCNPJInvalido() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Vendedor("123456", "Empresa A", "empresa@gmail.com")
        );
        assertEquals("O CNPJ esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Vendedor("12.345.678/0001-95", null, "empresa@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Vendedor("12.345.678/0001-95", "   ", "empresa@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeImproprio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Vendedor("12.345.678/0001-95", "fuck sobrenome", "empresa@gmail.com")
        );
        assertEquals("O nome contém palavras impróprias.", exception.getMessage());
    }
}

