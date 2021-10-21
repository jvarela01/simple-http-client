package com.redhat.nola.gps;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SimpleHttpClientTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/test-url")
          .then()
             .statusCode(200);
    }

}