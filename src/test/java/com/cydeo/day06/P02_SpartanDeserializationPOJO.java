package com.cydeo.day06;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.day06.pojo.Search;
import com.cydeo.day06.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;

public class P02_SpartanDeserializationPOJO extends SpartanTestBase {


            /*
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
}
         */

    @DisplayName("Get Single spartan for deerialization to POJO (spartan class)")
    @Test
    public void test1() {
        Response response =
        given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

              /*{
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
                  }*/
        JsonPath jsonPath = response.jsonPath();
        System.out.println("-------------- RESPONSE ----------------");
       Spartan spartan =  response.as(Spartan.class);
        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());
        // JSONPATH
        System.out.println("--------  spartan.getJSONPath() --------- ");
      Spartan spartanJson =  jsonPath.getObject("",Spartan.class);
        System.out.println("jsonPath.get(\"id\") = " + spartanJson.getId());
        System.out.println("jsonPath.get(\"name\") = " + spartanJson.getName());
        System.out.println("jsonPath.get(\"gender\") = " + spartanJson.getGender());
        System.out.println("jsonPath.get(\"phone\") = " + spartanJson.getPhone());

    }

@DisplayName("Get Spartans from search endpoint and deserialize to POJO")
    @Test
    public void test2() {
    Response response =
            given()
                    .accept(ContentType.JSON)

                    .when().get("/api/spartans/search")
                    .then()
                    .statusCode(200)
                    .extract().response();
    // I want to get the first spartan from content array and save into the spartan object
   // response.as("content[0]", Spartan.class);
    //we cannot do with as() method since it does not support path and class type at the same time
    JsonPath jsonPath = response.jsonPath();
   Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
    System.out.println("Spartan: " + spartan);
}


    @DisplayName("GET Spartans from search endpoint for deserialization to Search class")
    @Test
    public void test3() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        Search search = response.as(Search.class);
        System.out.println("search.getTotalElement() = " + search.getTotalElement());
        System.out.println("search.getContent() = " + search.getContent());

        //print second spartan from  content
        System.out.println("search.getContent().get(1) = " + search.getContent().get(1));

        //get me second spartan name
        System.out.println("search.getContent().get(1).getName() = " + search.getContent().get(1).getName());

    }


    @DisplayName("GET Spartans from search endpoint for deserialization to list of spartans")
    @Test
    public void test4() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
       List<Spartan> content = jsonPath.getList("content", Spartan.class);
        //System.out.println(content);
        for (Spartan spartan : content) {
            System.out.println("Spartan: " + spartan);
        }
    }

}
