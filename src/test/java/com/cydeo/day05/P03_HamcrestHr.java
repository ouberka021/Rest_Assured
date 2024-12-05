package com.cydeo.day05;

import com.cydeo.Utilities.HrTestBase;
import com.sun.source.tree.AssertTree;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;

public class P03_HamcrestHr extends HrTestBase {

    /*
      Given accept type is Json
      And parameters: q = {"job_id":"IT_PROG"}
      When users sends a GET request to "/employees"
      Then status code is 200
      And Content type is application/json
      Verify
          - each employees has manager
          - each employees working as IT_PROG
          - each of them getting salary greater than 3000
          - first names are .... (find proper method to check list against list)
          - emails without checking order (provide emails in different order,just make sure it has same emails)
          List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");
          "DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"
 */


    @Test
    public void verifyEmployeeInfo() {
        // expected firstnames
        List<String> expectedNames = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");// expected emailsList<String> expectedEmails = Arrays.asList("ALEXANDER_HUNOLD@example.com","BRUCE_LEE@example.com","DAVID_LEWIS@example.com","VALLI_JOHNSON@example.com","DIANNE_FORD@example.com");
        // Given accept type is Json
        given()
                .accept(ContentType.JSON)
                //And parameters: q = {"job_id":"IT_PROG"}
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                //When users sends a GET request to "/employees"
                .when().get("/employees")
                .then()
                //Then status code is 200
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("items.employee_id", everyItem(notNullValue()))
                .body("items.manager_id", everyItem(notNullValue()))
                .body("items.first_name", is(equalTo(expectedNames)))
                .body("items.last_name", everyItem(notNullValue()))
                .body("items.email", containsInAnyOrder("DAUSTIN", "AHUNOLD", "BERNST", "VPATABAL", "DLORENTZ"))
                .body("items.salary", everyItem(greaterThan(3000)));

    }

    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               first region id is 1
               four regions we have
               region names are not null
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4

               print all the regions names
               ...
               ..
               .
    */

    @Test
    public void task2() {
        // expected firstnames
        List<String> expectedNames = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");// expected emailsList<String> expectedEmails = Arrays.asList("ALEXANDER_HUNOLD@example.com","BRUCE_LEE@example.com","DAVID_LEWIS@example.com","VALLI_JOHNSON@example.com","DIANNE_FORD@example.com");
        // Given accept type is Json
        JsonPath jsonPath =
                given()
                        .log().ifValidationFails()
                        .accept(ContentType.JSON)
                //When users sends a GET request to "//regions"
                .when()
                        .get("/regions")
                .then()
                        //Then status code is 200
                       .statusCode(200)
                       .and()
                      //.log().headers()
                      // verify Date has values
                      .headers("Date", notNullValue())
                      // first region name is Europe
                      .body("items[0].region_name", equalTo("Europe"))
                      // first region id is 1
                     .body("items[0].region_id", equalTo(1))
                      // four regions we have
                     .body("items.size()", equalTo(4))
                     .body("items", hasSize(4))
                      // verify regions names are not null
                     .body("items.region_name", everyItem(notNullValue()))
                     // Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa".body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                     // region ids needs to be 1,2,3,4
                     .body("items.region_id", hasItems(1, 2, 3, 4))
                .extract().jsonPath();

        List<String> regionNames = jsonPath.getList("items.region_name");
        System.out.println("Region names: " + regionNames);
        for (String regionName : regionNames){
            System.out.println(regionName);
            if (regionName.equals("Middle East and Africa")){
                System.out.println(regionName.length() + "");
            }
        }

    }


}
