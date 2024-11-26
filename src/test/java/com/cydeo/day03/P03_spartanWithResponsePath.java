package com.cydeo.day03;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_spartanWithResponsePath extends SpartanTestBase {
    /* task response path-----
    Given accept type is json And path param id is 10
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json"
    And response payload values match the following:
    id is 10
    name is "Lorenza"
    gender is "Female"
    phone is 3312820936 */
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

        //verify response payload values match the following:
            // id is 10
            // name is "Lorenza"
            //  gender is "Female"
            // phone is 3312820936
        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String gender = response.path("gender").toString();
        String phone = response.path("phone").toString();

        System.out.println("Id :"+id);
        System.out.println("Name :"+name);
        System.out.println("Gender :"+gender);
        System.out.println("Phone :"+phone);
       // if the key value is not exist it will return null
       // String address = response.path("address");// return mul

        //Assert.assertEquals
        assertEquals("10", id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals("3312820936", phone);


    }



    @DisplayName("Get all spartans")
    @Test
    public void task2() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");
        //get me first spartan ID
        int spartanId = response.path("[0].id");
        // or we can use response.path("id[0]")
        System.out.println(spartanId);

        // get the name where index equals 4
        String name4 = response.path("name[4]");
        String name2 = response.path("[2].name");

        System.out.println("[4].name The name is : "+name4);
        System.out.println("name[2] The name is : "+name2);

        // get the last spartan  name[-1]
        //the operation [-1].name is not working with the last spartan
        String Lastname = response.path("name[-1]");
        System.out.println("The last spartan name is : "+Lastname);


        // get the second spartan before last name[-2]
        String Lastname_2 = response.path("name[-2]");
        System.out.println("The second before last spartan name is : "+Lastname_2);

        String Lastname_3 = response.path("name[-3]");
        System.out.println("The second before last spartan name is : "+Lastname_3);
       // get all spartan names:
        System.out.println("----------------- all spartan names ---------------");
        List<String> spartanNames = response.path("name");
        for (String each : spartanNames) {
            System.out.println("Each name : "+each);
        }



    }


}
