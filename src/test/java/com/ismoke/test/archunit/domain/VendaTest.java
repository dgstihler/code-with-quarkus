package com.ismoke.test.archunit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.models.Produto;
import com.ismoke.domain.models.Venda;
import com.ismoke.domain.models.Vendedor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

public class VendaTest {

    @Test
    public void deveCriarVendaComDadosValidos() {
        Comprador comprador = new Comprador("123.456.789-00", "Diogo Galdino", "diogo.galdino@gmail.com");
        Vendedor vendedor = new Vendedor("12.345.678/0001-95", "Empresa A", "empresa@gmail.com");
        Produto produto1 = new Produto("1", "Produto 1", new BigDecimal("10.50"), new BigDecimal("2"));
        Produto produto2 = new Produto("2", "Produto 2", new BigDecimal("20.00"), new BigDecimal("1"));

        List<Produto> produtos = List.of(produto1, produto2);

        Venda venda = new Venda(new BigDecimal("1"), comprador, vendedor, produtos);

        assertNotNull(venda);
        assertEquals(comprador, venda.getComprador());
        assertEquals(vendedor, venda.getVendedor());
        assertEquals(produtos, venda.getProdutos());
        assertEquals(new BigDecimal("41.00"), venda.getValorTotal());
        assertNotNull(venda.getDataVenda());
    }

    @Test
    public void deveCalcularValorTotalCorretamente() {
        Produto produto1 = new Produto("1", "Produto 1", new BigDecimal("5.00"), new BigDecimal("3")); // 15.00
        Produto produto2 = new Produto("2", "Produto 2", new BigDecimal("10.00"), new BigDecimal("2")); // 20.00

        List<Produto> produtos = List.of(produto1, produto2);
        Comprador comprador = new Comprador("123.456.789-00", "Diogo Galdino", "diogo.galdino@gmail.com");
        Vendedor vendedor = new Vendedor("12.345.678/0001-95", "Empresa B", "empresa@gmail.com");

        Venda venda = new Venda(new BigDecimal("2"), comprador, vendedor, produtos);

        assertEquals(new BigDecimal("35.00"), venda.getValorTotal()); // 15.00 + 20.00
    }

    @Test
    public void deveLancarExcecaoSeListaDeProdutosForVazia() {
        Comprador comprador = new Comprador("123.456.789-00", "Diogo Galdino", "diogo.galdino@gmail.com");
        Vendedor vendedor = new Vendedor("12.345.678/0001-95", "Empresa C", "empresa@gmail.com");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Venda(new BigDecimal("3"), comprador, vendedor, List.of())
        );

        assertEquals("A lista de produtos não pode ser vazia.", exception.getMessage());
    }

    @Test
    public void deveRegistrarDataDeVendaAutomatica() {
        Comprador comprador = new Comprador("123.456.789-00", "Diogo Galdino", "diogo.galdino@gmail.com");
        Vendedor vendedor = new Vendedor("12.345.678/0001-95", "Empresa A", "empresa@gmail.com");
        Produto produto = new Produto("1", "Produto 1", new BigDecimal("10.00"), new BigDecimal("1"));

        Venda venda = new Venda(new BigDecimal("4"), comprador, vendedor, List.of(produto));

        assertNotNull(venda.getDataVenda());
        assertTrue(venda.getDataVenda().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(venda.getDataVenda().isAfter(LocalDateTime.now().minusSeconds(1)));
    }
}
