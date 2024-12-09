package com.ismoke.test.archunit.presentation.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CompradorControllerTest {

    @Test
    void shouldCreateCompradorAndFindByCpf() {
        // Act: Criar um comprador via endpoint
        given()
            .contentType("application/json")
            .queryParam("cpf", "12345678900")
            .queryParam("nome", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/compradores")
            .then()
            .statusCode(201)
            .body("cpf", equalTo("12345678900"))
            .body("nome", equalTo("Diogo Galdino"))
            .body("email", equalTo("diogo.galdino@gmail.com"));

        // Assert: Buscar o comprador pelo CPF
        given()
            .contentType("application/json")
            .when()
            .get("/compradores/12345678900")
            .then()
            .statusCode(200)
            .body("cpf", equalTo("12345678900"))
            .body("nome", equalTo("Diogo Galdino"))
            .body("email", equalTo("diogo.galdino@gmail.com"));

        given()
            .when()
            .delete("/compradores/12345678900")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldListAllCompradoresFromDatabase() {
        // Arrange: Criar dois compradores
        given()
            .contentType("application/json")
            .queryParam("cpf", "12345678900")
            .queryParam("nome", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/compradores");

        given()
            .contentType("application/json")
            .queryParam("cpf", "98765432100")
            .queryParam("nome", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/compradores");

        // Act & Assert: Listar todos os compradores
        given()
            .contentType("application/json")
            .when()
            .get("/compradores")
            .then()
            .statusCode(200)
            .body("$.size()", equalTo(2))
            .body("[0].cpf", equalTo("12345678900"))
            .body("[1].cpf", equalTo("98765432100"));

        given()
            .when()
            .delete("/compradores/12345678900")
            .then()
            .statusCode(204);

        given()
            .when()
            .delete("/compradores/98765432100")
            .then()
            .statusCode(204);
    }

    @Test
    void shouldDeleteCompradorAndRemoveFromDatabase() {

        // Arrange: Criar um comprador antes de deletar
        given()
            .queryParam("cpf", "12345678900")
            .queryParam("nome", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/compradores");

        // Act: Deletar o comprador via endpoint
        given()
            .when()
            .delete("/compradores/12345678900")
            .then()
            .statusCode(204);

        // Assert: Validar que o comprador foi removido
        given()
            .when()
            .get("/compradores/12345678900")
            .then()
            .statusCode(404);
    }
}
