package com.ismoke.test.archunit.domain;

import com.ismoke.domain.models.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoComDadosValidos() {
        Produto produto = new Produto("123", "Produto A", BigDecimal.valueOf(2.99), BigDecimal.valueOf(10.0));
        assertNotNull(produto);
        assertEquals("123", produto.getId());
        assertEquals("Produto A", produto.getNome());
        assertEquals(BigDecimal.valueOf(10.0), produto.getQuantidade());
        assertEquals(BigDecimal.valueOf(2.99), produto.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Produto(null, "Produto A", BigDecimal.valueOf(2.99), BigDecimal.valueOf(10.0))
        );
        assertEquals("O ID do produto é obrigatório.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForMenorOuIgualAZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Produto("123", "Produto A", BigDecimal.ZERO, BigDecimal.valueOf(10.0))
        );
        assertEquals("O preço deve ser maior que zero.", exception.getMessage());

        IllegalArgumentException exceptionNegativo = assertThrows(
            IllegalArgumentException.class,
            () -> new Produto("123", "Produto A", BigDecimal.valueOf(-1), BigDecimal.valueOf(10.0))
        );
        assertEquals("O preço deve ser maior que zero.", exceptionNegativo.getMessage());
    }
}