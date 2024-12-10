package com.ismoke.test.archunit.presentation.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CustomerControllerTest {

    @Test
    void shouldCreateCustomerAndFindByCpf() {
        // Act: Criar um comprador via endpoint
        given()
            .contentType("application/json")
            .queryParam("cpf", "12345678900")
            .queryParam("name", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/customers")
            .then()
            .statusCode(201)
            .body("cpf", equalTo("12345678900"))
            .body("name", equalTo("Diogo Galdino"))
            .body("email", equalTo("diogo.galdino@gmail.com"));

        // Assert: Buscar o comprador pelo CPF
        given()
            .contentType("application/json")
            .when()
            .get("/customers/12345678900")
            .then()
            .statusCode(200)
            .body("cpf", equalTo("12345678900"))
            .body("name", equalTo("Diogo Galdino"))
            .body("email", equalTo("diogo.galdino@gmail.com"));

        given()
            .when()
            .delete("/customers/12345678900")
            .then()
            .statusCode(200);
    }

    @Test
    void shouldListAllEmptyCustomers() {

        // Act & Assert: Listar todos os customers
        given()
            .contentType("application/json")
            .when()
            .get("/customers")
            .then()
            .statusCode(404);
    }

    @Test
    void shouldListAllCustomersFromDatabase() {
        // Arrange: Criar dois customers
        given()
            .contentType("application/json")
            .queryParam("cpf", "12345678900")
            .queryParam("name", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/customers");

        given()
            .contentType("application/json")
            .queryParam("cpf", "98765432100")
            .queryParam("name", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/customers");

        // Act & Assert: Listar todos os customers
        given()
            .contentType("application/json")
            .when()
            .get("/customers")
            .then()
            .statusCode(200)
            .body("$.size()", equalTo(2))
            .body("[0].cpf", equalTo("12345678900"))
            .body("[1].cpf", equalTo("98765432100"));

        given()
            .when()
            .delete("/customers/12345678900")
            .then()
            .statusCode(200);

        given()
            .when()
            .delete("/customers/98765432100")
            .then()
            .statusCode(200);
    }

    @Test
    void shouldDeleteCustomerAndRemoveFromDatabase() {

        // Arrange: Criar um comprador antes de deletar
        given()
            .queryParam("cpf", "12345678900")
            .queryParam("name", "Diogo Galdino")
            .queryParam("email", "diogo.galdino@gmail.com")
            .when()
            .post("/customers");

        // Act: Deletar o comprador via endpoint
        given()
            .when()
            .delete("/customers/12345678900")
            .then()
            .statusCode(200);

        // Assert: Validar que o comprador foi removido
        given()
            .when()
            .get("/customers/12345678900")
            .then()
            .statusCode(404);
    }
}
