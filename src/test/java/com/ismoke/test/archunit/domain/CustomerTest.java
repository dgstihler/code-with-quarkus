package com.ismoke.test.archunit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ismoke.domain.models.Customer;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void deveCriarCompradorComDadosValidos() {
        Customer customer = new Customer("123.456.789-09", "Diogo Galdino", "diogo.galdino@gmail.com");
        assertNotNull(customer);
        assertEquals("123.456.789-09", customer.getCpf());
        assertEquals("Diogo Galdino", customer.getName());
        assertEquals("diogo.galdino@gmail.com", customer.getEmail());
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("123.456.789-09", null, "diogo.galdino@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("123.456.789-09", "   ", "diogo.galdino@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCPFInvalidoOuVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("12345", "Diogo Galdino", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoParaNomeImproprio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("123.456.789-09", "fuck sobrenome", "diogo.galdino@gmail.com")
        );
        assertEquals("O nome contém palavras impróprias.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailInvalido() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("123.456.789-09", "Diogo Galdino", "emailinvalido")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("05555982925", "Diogo", "    ")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("05555982925", "Diogo Galdino", null)
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoParaCpfVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer("    ", "Diogo", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCPFNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Customer(null, "Diogo Galdino", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }
}

