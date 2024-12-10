package com.ismoke.test.archunit.presentation.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SellerControllerTest {

     @Test
    void shouldCreateSellerAndFindByCNPJ() {
        // Act: Criar um vendedor via endpoint
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("name", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/sellers")
            .then()
            .statusCode(201)
            .body("cnpj", equalTo("12345678000199"))
            .body("name", equalTo("Empresa do Galdino"))
            .body("email", equalTo("contato@galdino.com"));

        // Assert: Buscar diretamente na infraestrutura para validar persistência
        given()
            .when()
            .get("/sellers/12345678000199")
            .then()
            .statusCode(200)
            .body("cnpj", equalTo("12345678000199"))
            .body("name", equalTo("Empresa do Galdino"))
            .body("email", equalTo("contato@galdino.com"));

        given()
            .when()
            .delete("/sellers/12345678000199")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldListAllEmptySellers() {

        // Act & Assert: Listar todos os vendedores
        given()
            .contentType("application/json")
            .when()
            .get("/sellers")
            .then()
            .statusCode(404);
    }

    @Test
    void shouldListAllSellersFromDatabase() {
        // Arrange: Criar dois vendedores
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("name", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/sellers");

        given()
            .contentType("application/json")
            .queryParam("cnpj", "98765432000188")
            .queryParam("name", "Empresa do Diogo")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/sellers");

        // Act & Assert: Listar todos os vendedores
        given()
            .when()
            .get("/sellers")
            .then()
            .statusCode(200)
            .body("$.size()", equalTo(2))
            .body("[0].cnpj", equalTo("12345678000199"))
            .body("[1].cnpj", equalTo("98765432000188"));

        given()
            .when()
            .delete("/sellers/12345678000199")
            .then()
            .statusCode(204);

        given()
            .when()
            .delete("/sellers/98765432000188")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldReturnNotFoundWhenSellerDoesNotExist() {
        given()
            .when()
            .get("/sellers/00000000000000")
            .then()
            .statusCode(404);
    }

    @Test
    void shouldDeleteSellerByCnpj() {
        // Arrange: Criar um vendedor antes de deletar
        given()
            .contentType("application/json")
            .queryParam("cnpj", "12345678000199")
            .queryParam("name", "Empresa do Galdino")
            .queryParam("email", "contato@galdino.com")
            .when()
            .post("/sellers");

        // Act: Deletar o vendedor via endpoint
        given()
            .when()
            .delete("/sellers/12345678000199")
            .then()
            .statusCode(204);

        // Assert: Validar que o vendedor foi removido
        given()
            .when()
            .get("/sellers/12345678000199")
            .then()
            .statusCode(404);
    }
}
