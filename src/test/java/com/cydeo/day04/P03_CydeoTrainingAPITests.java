package com.cydeo.day04;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P03_CydeoTrainingAPITests {


    //Given accept type is application/ json
    //And query param fid is 2
    //When user send request /student/fid}
    //  response.prettyPrint();
    //Then status code should be 200
    //And content type is application/json; charset=UTF-8
    // Assertions.assertEquals(200,response.getStatusCode());
//And Date header is exist
//And Server header is envoy
//And verify following
//firstName Mark
//batch 13
//major math
//
//emailAddress mark@email.com
//
//companyName Cydeo
//street 777 5th Ave
//
//zipCode 33222

    @DisplayName("Get /student/2")
    @Test
    public void task1() {
        String path = "http://3.86.13.225:8080";
        Response response = given()
                .accept("application/json")
                .and()
                .pathParams("id", "2")
                .when().get(path+"/student/{id}");
        // verify status code
        assertEquals(200, response.getStatusCode());
        // verify content type
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        // verify content date is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //And Server header is envoy
        assertEquals("envoy", response.header("Server"));
       //And verify following
       //firstName Mark
        //batch 13
        //major math
        //emailAddress mark@email.com
        // companyName Cydeo
        //street 777 5th Ave
        //zipCode 33222
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Mark", jsonPath.getString("students[0].firstName"));
        assertEquals(13, jsonPath.getInt("students[0].batch"));
        assertEquals("math", jsonPath.getString("students[0].major"));
        assertEquals("mark@email.com", jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Cydeo", jsonPath.getString("students[0].company.companyName"));
        assertEquals("777 5th Ave", jsonPath.getString("students[0].company.address.street"));
        assertEquals("33222", jsonPath.getString("students[0].company.address.zipCode"));



    }
}
