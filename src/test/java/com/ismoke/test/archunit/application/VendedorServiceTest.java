package com.ismoke.test.archunit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ismoke.application.dtos.VendedorDTO;
import com.ismoke.application.services.VendedorService;
import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.repositories.VendedorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VendedorServiceTest {

    private final VendedorRepository vendedorRepository = Mockito.mock(VendedorRepository.class);
    private final VendedorService vendedorService = new VendedorService(vendedorRepository);

    @Test
    void shouldCreateVendedorAndReturnDTO() {
        // Act
        VendedorDTO vendedorDTO = vendedorService.criarVendedor("12345678000199", "Galdino", "Galdino@gmail.com");

        // Assert
        assertEquals("12345678000199", vendedorDTO.getCnpj());
        assertEquals("Galdino", vendedorDTO.getNome());
        assertEquals("Galdino@gmail.com", vendedorDTO.getEmail());
    }

    @Test
    void shouldListAllVendedoresAndMapToDTOs() {

        // Arrange
        List<Vendedor> mockVendedores = List.of(
            new Vendedor("12345678000199", "Loja X", "contato@lojax.com"),
            new Vendedor("98765432000188", "Loja Y", "contato@lojay.com")
        );

        Mockito.when(vendedorRepository.listarTodos()).thenReturn(mockVendedores);

        // Act
        List<VendedorDTO> vendedores = vendedorService.listarTodosVendedores();

        // Assert
        assertEquals(2, vendedores.size());
        assertEquals("12345678000199", vendedores.get(0).getCnpj());
        assertEquals("Loja X", vendedores.get(0).getNome());
        assertEquals("98765432000188", vendedores.get(1).getCnpj());
        assertEquals("Loja Y", vendedores.get(1).getNome());
    }

    @Test
    void shouldFindVendedorByCnpjAndMapToDTO() {
        // Arrange
        Vendedor mockVendedor = new Vendedor("12345678000199", "Loja X", "contato@lojax.com");
        Mockito.when(vendedorRepository.buscarPorCnpj("12345678000199")).thenReturn(Optional.of(mockVendedor));

        // Act
        Optional<VendedorDTO> vendedorOptional = vendedorService.buscarVendedorPorCnpj("12345678000199");

        // Assert
        assertTrue(vendedorOptional.isPresent());
        VendedorDTO vendedorDTO = vendedorOptional.get();
        assertEquals("12345678000199", vendedorDTO.getCnpj());
        assertEquals("Loja X", vendedorDTO.getNome());
    }

    @Test
    void shouldReturnEmptyWhenVendedorNotFoundByCnpj() {
        // Arrange
        Mockito.when(vendedorRepository.buscarPorCnpj("12345678000199")).thenReturn(Optional.empty());

        // Act
        Optional<VendedorDTO> vendedorOptional = vendedorService.buscarVendedorPorCnpj("12345678000199");

        // Assert
        assertFalse(vendedorOptional.isPresent());
    }
}
