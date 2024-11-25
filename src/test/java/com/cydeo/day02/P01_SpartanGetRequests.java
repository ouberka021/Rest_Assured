package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class P01_SpartanGetRequests {
    String url = "http://18.209.59.60:8000";
    String spartanUrl = url + "/api/spartans";

    /*
    Given content type is application/json
    When users send GET request to /api/spartans endpoint
    Then users should get status code 200
    And content type should be application/json
     */
    @Test
    public void test1() {
      Response response =  RestAssured.given()
                .accept(ContentType.JSON) // Hi api please send me json content
                .when()
                .get(url + "/api/spartans/3");
      // print the response body
        response.prettyPrint();
       int actualStatusCode= response.statusCode();
        System.out.println("Actual status: " + actualStatusCode);
        // verify that the status code is 200
        Assertions.assertEquals(200,actualStatusCode);
        // how to get response content type header?
        String actualContentType1 = response.contentType();
        String actualContentType = response.header("Content-Type");
        System.out.println("actual Content Type : "+actualContentType1);
        // assert the content type
        Assertions.assertEquals("application/json", actualContentType);
        // how to get connection  header value?
        String connectionHeader = response.header("Connection");
        System.out.println("Connection Header : "+connectionHeader);
        // how to get date  header value?
        String dateHeader = response.header("Date");
        System.out.println("Date Header : "+dateHeader);
        // how to verify header exist?
        //hasHeaderWithName method help us to verify header exist or not
        //it is useful for dynamic header values, we are only verifying
        // header exist or not
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);
    }

    /*
    Given  content type is application/json
    When user sends GET request /api/spartans/3 endpoint
    Then status code should be 200
    And content type should be application/json
    And response body needs to contains Fidole
     */
    @Test
    public void test2() {
        Response response =  RestAssured.given()
                .accept(ContentType.JSON) // Hi api please send me json content
                .when()
                .get(url + "/api/spartans/3");
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
        String actualContentType = response.contentType();
        System.out.println(actualContentType);
        Assertions.assertEquals("application/json", actualContentType);
        response.prettyPrint();
        Assertions.assertTrue( response.asString().contains("3"));

    }
     /*
    Given  no header provided
    When user sends GET request /api/hello
    Then status code should be 200
    And content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from sparta"
     */
    @Test
    public void test3() {
        Response response =  RestAssured.get(url + "/api/hello");
        System.out.println(response.statusCode());

        Assertions.assertEquals(200, response.statusCode());

        String actualContentType = response.header("Content-Type");
        System.out.println(actualContentType);
        Assertions.assertEquals("text/plain;charset=UTF-8", actualContentType);

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
      Assertions.assertEquals("17", response.header("Content-Length"));
     Assertions.assertTrue( response.prettyPrint().equals("Hello from Sparta"));
    }


}
