package com.ismoke.test.archunit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ismoke.domain.models.Seller;
import org.junit.jupiter.api.Test;

class SellerTest {

    @Test
    void deveCriarVendedorComCNPJValido() {
        Seller seller = new Seller("12.345.678/0001-95", "Empresa A", "empresa@gmail.com");
        assertNotNull(seller);
        assertEquals("12.345.678/0001-95", seller.getCnpj());
        assertEquals("Empresa A", seller.getName());
        assertEquals("empresa@gmail.com", seller.getEmail());
    }

    @Test
    void deveLancarExcecaoParaCNPJInvalido() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("123456", "Empresa A", "empresa@gmail.com")
        );
        assertEquals("O CNPJ esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", null, "empresa@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", "   ", "empresa@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeImproprio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", "fuck sobrenome", "empresa@gmail.com")
        );
        assertEquals("O nome contém palavras impróprias.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailInvalido() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", "Diogo Galdino", "emailinvalido")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", "Diogo", "    ")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("12.345.678/0001-95", "Diogo Galdino", null)
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoParaCNPJVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller("    ", "Diogo", "diogo.galdino@gmail.com")
        );
        assertEquals("O CNPJ esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCNPJNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Seller(null, "Diogo Galdino", "diogo.galdino@gmail.com")
        );
        assertEquals("O CNPJ esta inválido ou vazio.", exception.getMessage());
    }
}

