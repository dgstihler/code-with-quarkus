package com.ismoke.test.archunit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ismoke.application.dtos.SellerDTO;
import com.ismoke.application.services.SellerService;
import com.ismoke.domain.models.Seller;
import com.ismoke.domain.repositories.SellerRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SellerServiceTest {

    private final SellerRepository sellerRepository = Mockito.mock(SellerRepository.class);
    private final SellerService sellerService = new SellerService(sellerRepository);

    @Test
    void shouldCreateVendedorAndReturnDTO() {
        // Act
        SellerDTO sellerDTO = sellerService.criarVendedor("12345678000199", "Galdino", "Galdino@gmail.com");

        // Assert
        assertEquals("12345678000199", sellerDTO.getCnpj());
        assertEquals("Galdino", sellerDTO.getName());
        assertEquals("Galdino@gmail.com", sellerDTO.getEmail());
    }

    @Test
    void shouldListAllVendedoresAndMapToDTOs() {

        // Arrange
        List<Seller> mockVendedores = List.of(
            new Seller("12345678000199", "Loja X", "contato@lojax.com"),
            new Seller("98765432000188", "Loja Y", "contato@lojay.com")
        );

        Mockito.when(sellerRepository.listAllSellers()).thenReturn(mockVendedores);

        // Act
        List<SellerDTO> vendedores = sellerService.listarTodosVendedores();

        // Assert
        assertEquals(2, vendedores.size());
        assertEquals("12345678000199", vendedores.get(0).getCnpj());
        assertEquals("Loja X", vendedores.get(0).getName());
        assertEquals("98765432000188", vendedores.get(1).getCnpj());
        assertEquals("Loja Y", vendedores.get(1).getName());
    }

    @Test
    void shouldFindVendedorByCnpjAndMapToDTO() {
        // Arrange
        Seller mockSeller = new Seller("12345678000199", "Loja X", "contato@lojax.com");
        Mockito.when(sellerRepository.findByCNPJ("12345678000199")).thenReturn(Optional.of(mockSeller));

        // Act
        Optional<SellerDTO> vendedorOptional = sellerService.buscarVendedorPorCnpj("12345678000199");

        // Assert
        assertTrue(vendedorOptional.isPresent());
        SellerDTO sellerDTO = vendedorOptional.get();
        assertEquals("12345678000199", sellerDTO.getCnpj());
        assertEquals("Loja X", sellerDTO.getName());
    }

    @Test
    void shouldReturnEmptyWhenVendedorNotFoundByCnpj() {
        // Arrange
        Mockito.when(sellerRepository.findByCNPJ("12345678000199")).thenReturn(Optional.empty());

        // Act
        Optional<SellerDTO> vendedorOptional = sellerService.buscarVendedorPorCnpj("12345678000199");

        // Assert
        assertFalse(vendedorOptional.isPresent());
    }
}
