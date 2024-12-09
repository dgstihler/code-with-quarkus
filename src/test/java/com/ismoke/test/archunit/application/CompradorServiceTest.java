package com.ismoke.test.archunit.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ismoke.application.dtos.CompradorDTO;
import com.ismoke.application.services.CompradorService;
import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.repositories.CompradorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CompradorServiceTest {

    private final CompradorRepository compradorRepository = Mockito.mock(CompradorRepository.class);
    private final CompradorService compradorService = new CompradorService(compradorRepository);

    @Test
    void shouldCreateCompradorAndReturnDTO() {

        CompradorDTO compradorDTO = compradorService.criarComprador("05555982925", "Diogo Galdino", "dgstihler@gmail.com");

        assertEquals("05555982925", compradorDTO.getCpf());
        assertEquals("Diogo Galdino", compradorDTO.getNome());
        assertEquals("dgstihler@gmail.com", compradorDTO.getEmail());
    }

    @Test
    void shouldListAllCompradoresAndMapToDTOs() {
        // Arrange
        List<Comprador> mockCompradores = List.of(
            new Comprador("12345678900", "Diogo", "galdino@email.com"),
            new Comprador("98765432100", "Galdino", "diogo@email.com")
        );
        when(compradorRepository.listarTodos()).thenReturn(mockCompradores);

        // Act
        List<CompradorDTO> compradores = compradorService.listarTodosCompradores();

        // Assert
        assertEquals(2, compradores.size());
        assertEquals("12345678900", compradores.get(0).getCpf());
        assertEquals("Diogo", compradores.get(0).getNome());
        assertEquals("galdino@email.com", compradores.get(0).getEmail());

        assertEquals("98765432100", compradores.get(1).getCpf());
        assertEquals("Galdino", compradores.get(1).getNome());
        assertEquals("diogo@email.com", compradores.get(1).getEmail());

    }

    @Test
    void shouldFindCompradorByCpfAndMapToDTO() {
        // Arrange
        Comprador mockComprador = new Comprador("12345678900", "Galdino", "dgstihler@gmail.com");
        when(compradorRepository.buscarPorCpf("12345678900")).thenReturn(java.util.Optional.of(mockComprador));

        // Act
        Optional<CompradorDTO> compradorOptional = compradorService.buscarCompradorPorCpf("12345678900");

        // Assert
        assertTrue(compradorOptional.isPresent());
        CompradorDTO compradorDTO = compradorOptional.get();
        assertEquals("12345678900", compradorDTO.getCpf());
        assertEquals("Galdino", compradorDTO.getNome());
        assertEquals("dgstihler@gmail.com", compradorDTO.getEmail());
    }

    @Test
    void shouldReturnEmptyWhenCompradorNotFoundByCpf() {
        // Arrange
        when(compradorRepository.buscarPorCpf("12345678900")).thenReturn(java.util.Optional.empty());

        // Act
        Optional<CompradorDTO> compradorOptional = compradorService.buscarCompradorPorCpf("12345678900");

        // Assert
        assertFalse(compradorOptional.isPresent());
    }

}
