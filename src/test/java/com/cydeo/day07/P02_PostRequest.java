package com.cydeo.day07;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanClass;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_PostRequest extends SpartanTestBase {
    /**
     * Given accept type is JSON
     * And Content type is JSON
     * And request json body is:
     * {
     * "gender": "Male",
     * "name": "John Doe",
     * "phone": 8877445596
     * }
     * When user sends POST request to '/api/spartans'
     * Then status code 201
     * And content type should be application/ json And json payload/response/body should contain: verify the success value is 'A Spartan is Born!'
     * "name": "John Doe",
     * "gender": "Male",
     * "phone": 1231231231
     */
    @DisplayName("Post Spartan with String body")
    @Test
    public void post() {
        String bodyString = "{\"gender\": \"Male\",\"name\": \"John Doe\",\"phone\": 5577445596}";
        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(bodyString)
                        .when()
                        .post("/api/spartans")
                        .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("message", equalTo("A Spartan is Born!"))
                        .extract().jsonPath();

        // Verify the success value is 'A Spartan is Born!'
        assertEquals("A Spartan is Born!", jsonPath.getString("message"));
        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("5577445596", jsonPath.getString("data.phone"));
        int id = jsonPath.getInt("data.id");
        System.out.println("Actual ID is: " + id);

    }


    @DisplayName("Post Spartan with Map body")
    @Test
    public void post2() {
        Map<String, Object> body = new HashMap<>();
        body.put("gender", "Male");
        body.put("name", "John Hash");
        body.put("phone", "5512349998");
        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post("/api/spartans")
                        .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("message", equalTo("A Spartan is Born!"))
                        .extract().jsonPath();

        // Verify the success value is 'A Spartan is Born!'
        assertEquals("A Spartan is Born!", jsonPath.getString("message"));
        assertEquals("John Hash", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("5512349998", jsonPath.getString("data.phone"));
        int id = jsonPath.getInt("data.id");
        System.out.println("Actual ID is: " + id);

    }


    @DisplayName("Post Spartan with spartan POJO")
    @Test
    public void post3() {
        Spartan spartan = new Spartan(
                "John Hassan",
                "Male",
                "5512349998"
        );
        JsonPath jsonPath =
                given().log().body()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(spartan)
                        .when()
                        .post("/api/spartans")
                        .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("message", equalTo("A Spartan is Born!"))
                        .extract().jsonPath();

        // Verify the success value is 'A Spartan is Born!'
        assertEquals("A Spartan is Born!", jsonPath.getString("message"));
        assertEquals("John Hassan", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("5512349998", jsonPath.getString("data.phone"));
        int id = jsonPath.getInt("data.id");
        System.out.println("Actual ID is: " + id);

    }

    @DisplayName("Post Spartan with spartan POJO School practice")
    @Test
    public void post4() {
        SpartanClass spartan = new SpartanClass();
        spartan.setName("John Hassan");
        spartan.setGender("Male");
        spartan.setPhone(5512349998l);
        JsonPath jsonPath =
                given().log().body()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(spartan)
                        .when()
                        .post("/api/spartans")
                        .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("message", equalTo("A Spartan is Born!"))
                        .extract().jsonPath();

        // Verify the success value is 'A Spartan is Born!'
        assertEquals("A Spartan is Born!", jsonPath.getString("message"));
        assertEquals("John Hassan", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(5512349998l, jsonPath.getLong("data.phone"));
        int id = jsonPath.getInt("data.id");
        System.out.println("Actual ID is: " + id);

    }

    @DisplayName("Post Spartan with spartan POJO and get same Spartan")
    @Test
    public void post5() {
        //empty spartan object and we use setters to set some value
        //values can be from faker library or somewhere else which is changing dynamically
        SpartanClass spartanPOST = new SpartanClass();
        spartanPOST.setName("Harold Finch");
        spartanPOST.setGender("Male");
        spartanPOST.setPhone(1234567890l);
        spartanPOST.setId(500); //even if we put some id value, it didnt serialized because of the jackson annotation on the class

        System.out.println("spartan = " + spartanPOST);


        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON) //hey api, please send me JSON RESPONSE BODY
                .and()
                .contentType(ContentType.JSON) // hey api, I am sending you JSON REQUEST BODY
                .body(spartanPOST) //it will take spartan object and serialize to JSON
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message",is("A Spartan is Born!"))
                .extract().jsonPath();

        //request body verification
        assertEquals("Harold Finch",jsonPath.getString("data.name"));
        assertEquals("Male",jsonPath.getString("data.gender"));
        assertEquals(1234567890l,jsonPath.getLong("data.phone"));

        //I want to get id out of the response body, to delete or send get request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

        //SEND GET REQUEST TO THE SPARTAN THAT IS CREATED THEN DESERIALIZE TO SPARTAN CLASS and compare

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
        //get json response and deserialize to spartan class
        SpartanClass spartanGET = response.as(SpartanClass.class);
//
        System.out.println("spartanGET = " + spartanGET);
//
//        //verify names that we sent and get is matching
        assertEquals(spartanPOST.getName(),spartanGET.getName());

    }

}
