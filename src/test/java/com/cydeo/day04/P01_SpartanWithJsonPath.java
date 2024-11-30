package com.cydeo.day04;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartanWithJsonPath extends SpartanTestBase {



    @DisplayName("Get spartan with response path")
    @Test
    public void responsePath() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and().pathParam("id", "10")
                .when()
                .get("/api/spartans/{id}");
        response.prettyPrint();
//verify status code is 200
        assertEquals(200, response.statusCode());

        //Verify content-type is "application/json"
        assertEquals("application/json", response.contentType());

        //we saved response as JSONPATH object
       JsonPath jsonPath =  response.jsonPath();
       int id = jsonPath.getInt("id");
       String name = jsonPath.getString("name");
       String gender = jsonPath.getString("gender");
       long phone = jsonPath.getLong("phone");
        System.out.println("Id:"+id + "  Name:" + name + "  Gender:" + gender+"  Phone:" + phone);
        assertEquals("Lorenza", name);
        assertEquals(10, id);



    }

}
