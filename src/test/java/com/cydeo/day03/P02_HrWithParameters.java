package com.cydeo.day03;

import com.cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_HrWithParameters extends HrTestBase {

    @DisplayName("Get request /countries with Region_id ")
    @Test
    public void task1() {
//    Given accept type is Json
        Response response = given().accept(ContentType.JSON)
//    And parameters: q = "{"region_id" :2}"
                .and()
                .queryParam("q", "{\"region_id\":2}")
//    When users sends a GET request to "/countries"
                .when()
                .get("/countries");
//    Then status code is 200
        System.out.println("Status Code : " + response.getStatusCode());
        assertEquals(200, response.getStatusCode());

//    And Content type is application/json
        String type = response.header("Content-Type");
        System.out.println("Content Type : " + type);
        assertEquals("application/json", type);

//    And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));


    }




}
