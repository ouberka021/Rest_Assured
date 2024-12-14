package com.cydeo.day08;

import com.cydeo.Utilities.SpartanTestBaseAuth;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanAuthTest extends SpartanTestBaseAuth {
    @DisplayName("Get Spartan without authentication")
    @Test
    public void test1() {
        given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().log().all()
                .statusCode(401)
                .body("message", is("Unauthorized"));

    }

    @DisplayName("Get api/Spartan as user -->expect --> 401")
    @Test
    public void test2() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when().get("/api/spartans")
                .then()
                .statusCode(401)
                .log().all();
    }

    @DisplayName("Delete /api/spartans/{id} as editor -- expect --> 403 forbidden")
    @Test
    public void test3() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("editor", "editor")
                .pathParam("id", 10)
                .when().delete("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(403)
                .body("error",is("Forbidden"));
    }

    @DisplayName("Delete /api/spartans/{id} as admin -- expect --> 204 ")
    @Test
    public void test4() {
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 100)
                .when().delete("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }
}

