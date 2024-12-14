package com.cydeo.day07;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {


    @DisplayName("Put spartan with map")
    @Test
    public void putSpartan() {

        Map<String, Object> body = new HashMap<>();
        body.put("gender", "Male");
        body.put("name", "Mike Hash");
        body.put("phone", "5512349888");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .pathParam("id", 114)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }


    @DisplayName("Patch spartan with map")
    @Test
    public void patchSpartan() {

        Map<String, Object> body = new HashMap<>();

        body.put("name", "Ali Hassan");
        body.put("phone", "1234567879");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .pathParam("id", 114)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("Delete spartan with map")
    @Test
    public void deleteSpartan() {

//        Map<String, Object> body = new HashMap<>();
//        body.put("gender", "Male");
//        body.put("name", "Mike Hash");
//        body.put("phone", "5512349888");

        int id = 116;
        given()
                //.contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //after deleted when we send get request to id that we deleted, it needs to give 404 given RequestSpecification
        given().pathParam("id", id)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(404);


    }


}
