package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {


@BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://18.209.59.60:8000";
    }

/*
 * Given accept content type is application/json
 * When user sends GET request /api/spartans endpoint
 * Then status code should be 200
 */
@DisplayName("display content body")
    @Test
    public void test1() {
        Response response = RestAssured.given()
               .accept(ContentType.JSON)
               .when()
               .get("/api/spartans");
        assertEquals(200, response.statusCode());
        response.prettyPrint();

    }

    /*
    Given Accept type application/xml
    When user send GET request to /api/spantans/10 end point
    Then status code must be 406
    And response Content Type must be application/xmL;charset=UTF-8;
     */
    @DisplayName("accept, application/xml - 406")
    @Test
    public void test2() {
        Response response = RestAssured.given()
               .accept(ContentType.XML)
               .when()
               .get("/api/spartans/10");
        assertEquals(406, response.statusCode());
        System.out.println(response.header("Content-Type"));
        assertEquals("application/xml;charset=UTF-8", response.header("Content-Type"));
        response.prettyPrint();
    }



    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }







}
