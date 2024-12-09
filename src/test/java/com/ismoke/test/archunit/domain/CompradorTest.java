package com.ismoke.test.archunit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ismoke.domain.models.Comprador;
import org.junit.jupiter.api.Test;

class CompradorTest {

    @Test
    void deveCriarCompradorComDadosValidos() {
        Comprador comprador = new Comprador("123.456.789-09", "Diogo Galdino", "diogo.galdino@gmail.com");
        assertNotNull(comprador);
        assertEquals("123.456.789-09", comprador.getCpf());
        assertEquals("Diogo Galdino", comprador.getNome());
        assertEquals("diogo.galdino@gmail.com", comprador.getEmail());
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("123.456.789-09", null, "diogo.galdino@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("123.456.789-09", "   ", "diogo.galdino@gmail.com")
        );
        assertEquals("O nome é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCPFInvalidoOuVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("12345", "Diogo Galdino", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoParaNomeImproprio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("123.456.789-09", "fuck sobrenome", "diogo.galdino@gmail.com")
        );
        assertEquals("O nome contém palavras impróprias.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailInvalido() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("123.456.789-09", "Diogo Galdino", "emailinvalido")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("05555982925", "Diogo", "    ")
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaEmailNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("05555982925", "Diogo Galdino", null)
        );
        assertEquals("O email esta inválido ou vazio.", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoParaCpfVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador("    ", "Diogo", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCPFNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Comprador(null, "Diogo Galdino", "diogo.galdino@gmail.com")
        );
        assertEquals("O CPF esta inválido ou vazio.", exception.getMessage());
    }
}

