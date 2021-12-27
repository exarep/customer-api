package com.exarep.customer.individual;

import com.exarep.customer.MockData;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(CustomerIndividualResource.class)
public class CustomerIndividualResourceTest {

    @Test
    public void getAll() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200);
    }

    @Test
    public void getById() {
        CustomerIndividual saved = post(MockData.createCustomerIndividual());
        CustomerIndividual got = given()
                .when().get("/{customerIndividualId}", saved.getCustomerIndividualId())
                .then()
                .statusCode(200)
                .extract().as(CustomerIndividual.class);
        assertThat(saved).isEqualTo(got);
    }

    public CustomerIndividual post(CustomerIndividual customerIndividual) {
        CustomerIndividual saved = given()
                .contentType(ContentType.JSON)
                .body(customerIndividual)
                .post()
                .then()
                .statusCode(201)
                .extract().as(CustomerIndividual.class);
        assertThat(saved.getCustomerIndividualId()).isNotNull();
        return saved;
    }

    @Test
    public void postFailNoFirstName() {
        CustomerIndividual customer = MockData.createCustomerIndividual();
        customer.setFirstName(null);
        given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void put() {
        CustomerIndividual saved = post(MockData.createCustomerIndividual());
        saved.setFirstName("Updated");
        CustomerIndividual updated = given()
                .contentType(ContentType.JSON)
                .body(saved)
                .put("/{customerIndividualId}", saved.getCustomerIndividualId())
                .then()
                .statusCode(200)
                .extract().as(CustomerIndividual.class);
        assertThat(updated.getFirstName()).isEqualTo("Updated");
    }

}