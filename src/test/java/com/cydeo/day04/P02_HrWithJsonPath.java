package com.cydeo.day04;

import com.cydeo.Utilities.HrTestBase;
import groovyjarjarantlr4.v4.runtime.atn.SemanticContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_HrWithJsonPath extends HrTestBase {
@DisplayName("Get all countries")
    @Test
    public void task() {
    Response response = get("/countries");

    // Verify status code
    assertEquals(200, response.getStatusCode());

    //create jsonpath object
    JsonPath jsonPath = response.jsonPath();

    //get me 3rd country name
    System.out.println("3rd country name is :"+jsonPath.getString("items[2].country_name"));

    // get the 3rd and 4th countries name using items[2,3]
    System.out.println("3rd and 4th countries name is :"+jsonPath.getString("items[2,3].country_name"));

    // get all country name where region_id is 2
    List<String> list = jsonPath.getList("items.findAll {it.region_id == 2}.country_name");
    System.out.println("List countries :"+list);

   // response.prettyPrint();
}


@DisplayName("Get all /employees?limit=200 with JsonPath")
    @Test
    public void task2() {

        //  Given accept type is application/ json
      Response response =  given().accept(ContentType.JSON)
        //  And query param limit is 200
                .and().queryParam("limit","200")
        //  When user send request /employees Then user should see
                .when().get("/employees");

assertEquals(200,response.statusCode());
// create jsonPath object
JsonPath jsonPath = response.jsonPath();
    // get all emails from response
    List<String> emails = jsonPath.getList("items.email");
    System.out.println("List emails :"+emails);
    System.out.println("List emails :"+emails.size());
    // get all emails who is working as IT_PROG
    List<String> emails2 = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
    System.out.println("List emails of IT_PROG :"+emails2);
    System.out.println("List emails of IT_PROG :"+emails2.size());

    //get  me all employees first names whose salary is more than 10000
    List<String> employeesFN = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
    System.out.println("employees first names whose salary is more than 10000 :\n"+employeesFN);
    //get me all information from response who has max salary
    System.out.println("all information from response who has max salary:\n"+jsonPath.getString("items.max{it.salary}"));
    // get me first name from response who has max salary
    System.out.println("first name from response who has max salary:\n"+"--> "+jsonPath.getString("items.max{it.salary}.first_name"));
    // get me firstname from response who has min salary
    System.out.println("first name from response who has min salary:\n"+"--> "+jsonPath.getString("items.min{it.salary}.first_name"));
        response.prettyPrint();
    }

}
