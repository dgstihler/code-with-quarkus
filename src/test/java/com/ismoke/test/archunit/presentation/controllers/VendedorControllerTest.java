package com.ismoke.test.archunit.presentation.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import com.ismoke.application.services.VendedorService;
import com.ismoke.domain.repositories.VendedorRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class VendedorControllerTest {

    private final VendedorRepository vendedorRepository = Mockito.mock(VendedorRepository.class);
    private final VendedorService vendedorService = new VendedorService(vendedorRepository);

    @Test
    void shouldCreateVendedorAndFindByCNPJ() {
        // Act: Criar um vendedor via endpoint
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("nome", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/vendedores")
            .then()
            .statusCode(201)
            .body("cnpj", equalTo("12345678000199"))
            .body("nome", equalTo("Empresa do Galdino"))
            .body("email", equalTo("contato@galdino.com"));

        // Assert: Buscar diretamente na infraestrutura para validar persistência
        given()
            .when()
            .get("/vendedores/12345678000199")
            .then()
            .statusCode(200)
            .body("cnpj", equalTo("12345678000199"))
            .body("nome", equalTo("Empresa do Galdino"))
            .body("email", equalTo("contato@galdino.com"));

        given()
            .when()
            .delete("/vendedores/12345678000199")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldListAllEmptySellers() {

        // Act & Assert: Listar todos os vendedores
        given()
            .contentType("application/json")
            .when()
            .get("/vendedores")
            .then()
            .statusCode(404);
    }

    @Test
    void shouldListAllVendedoresFromDatabase() {
        // Arrange: Criar dois vendedores
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("nome", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/vendedores");

        given()
            .contentType("application/json")
            .queryParam("cnpj", "98765432000188")
            .queryParam("nome", "Empresa do Diogo")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/vendedores");

        // Act & Assert: Listar todos os vendedores
        given()
            .when()
            .get("/vendedores")
            .then()
            .statusCode(200)
            .body("$.size()", equalTo(2))
            .body("[0].cnpj", equalTo("12345678000199"))
            .body("[1].cnpj", equalTo("98765432000188"));

        given()
            .when()
            .delete("/vendedores/12345678000199")
            .then()
            .statusCode(204);

        given()
            .when()
            .delete("/vendedores/98765432000188")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldReturnNotFoundWhenVendedorDoesNotExist() {
        given()
            .when()
            .get("/vendedores/00000000000000")
            .then()
            .statusCode(404);
    }

    @Test
    void shouldDeleteVendedorByCnpj() {
        // Arrange: Criar um vendedor antes de deletar
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("nome", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/vendedores");

        // Act: Deletar o vendedor via endpoint
        given()
            .when()
            .delete("/vendedores/12345678000199")
            .then()
            .statusCode(204);

        // Assert: Validar que o vendedor foi removido
        given()
            .when()
            .get("/vendedores/12345678000199")
            .then()
            .statusCode(404);
    }
}
