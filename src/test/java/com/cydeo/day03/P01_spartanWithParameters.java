package com.cydeo.day03;

import com.cydeo.Utilities.BaseUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_spartanWithParameters extends BaseUtils {

//String url = "http://18.209.59.60:8000";

    @DisplayName("Get Spartan /api/spartans/{id} path param with 24")
    @Test
    public void task1() {


//Given accept type is Json
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParams("id", "24")
//And Id parameter value is 24
                .when().get("/api/spartans/{id}");
//When user sends GET request to /api/spartans/{id}
        response.prettyPrint();
//Then response status code should be 200
        int actualStatusCode = response.statusCode();
        assertEquals(200, actualStatusCode);

//And response content-type: application/json
        String actualContentType = response.header("Content-Type");
        System.out.println("actual Content Type : " + actualContentType);

//And "Julio" should be in response payload (body)
        boolean isJulio = response.body().asString().contains("Julio");
        assertTrue(isJulio);
    }



    @DisplayName("Get spartan /api/spartans/{id} with invalid id")
    @Test
    public void task2() {
        //        Given accept type is Json
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
//        And Id parameter value is 500
                .and().pathParams("id", "500")
//        When user sends GET request to /api/spartans/{id}
                .when().get("/api/spartans/{id}");
//        Then response status code should be 404 or HttpStatus.SC_NOT_FOUND
        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());// below one giving  the same result
        assertEquals(404, response.getStatusCode());
//        And response content-type: application/json
        String responseContentType = response.getContentType();
        System.out.println("Response Content Type : " + responseContentType);
        assertTrue(responseContentType.contains("application/json"));
//        And "Not Found" message should be in response payload
        System.out.println(response.body().asString());
        assertTrue(response.body().asString().contains("Not Found"));
    }



@DisplayName("Get Request with Query Parameters")
    @Test
    public void task3() {
        //    Given Accept type is Json And query parameter values are:
        Response response = RestAssured.given().accept(ContentType.JSON)
                //    gender | Female
                //    nameContains | e
                .and()
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "ne")
                //    When user sends GET request to /api/spartans/search
                .when()
                .get("/api/spartans/search");
        //    Then response status code should be 200
        assertEquals(200, response.statusCode());
        //    And response content-type: application/json And "Female" should be in response
        String type = response.header("Content-Type");
        assertTrue(type.contains("application/json"));
        assertTrue(response.body().asString().contains("Female"));
        //    payload And "Janette" should be in response payload
        assertTrue( response.body().asString().contains("Janette"));
response.prettyPrint();

    }



    @DisplayName("Get Request to /api/spartans/search with Query Parameters")
    @Test
    public void task4() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        //api/spartans/search?gender=Female&nameContains=e
        Response response = RestAssured.given().accept(ContentType.JSON)
                //    gender | Female
                //    nameContains | e
                .and()
                .queryParams(queryMap)
                //    When user sends GET request to /api/spartans/search
                .when()
                .get("/api/spartans/search");
        //    Then response status code should be 200
        assertEquals(200, response.statusCode());
        //    And response content-type: application/json And "Female" should be in response
        String type = response.header("Content-Type");
        assertTrue(type.contains("application/json"));
        assertTrue(response.body().asString().contains("Female"));
        //    payload And "Janette" should be in response payload
        assertTrue( response.body().asString().contains("Janette"));
        response.prettyPrint();

    }


}
