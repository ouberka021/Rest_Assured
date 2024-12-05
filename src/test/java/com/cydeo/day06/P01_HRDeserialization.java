package com.cydeo.day06;

import com.cydeo.Utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P01_HRDeserialization extends HrTestBase {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */
    @Test
    public void getLocation() {
        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/locations")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> firstObject = jsonPath.getMap("items[0]");
        System.out.println("jsonPath.getMap(\"items[0]\") = " + firstObject);

        // get the location id from the first object
        int firstId = jsonPath.getInt("items[0].location_id");
        System.out.println("jsonPath.getMap(\"items[0]\") = " + firstId);
        System.out.println("jsonPath.getMap(\"items[0]\") = " + firstObject.get("location_id"));

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        List<String> Links = jsonPath.getList("items.links");
        System.out.println("jsonPath.getMap(\"items.links\") = " + Links);

        // get the 3rd link from the list
        String firstLink = jsonPath.getString("items[2].links[0]");
        System.out.println("jsonPath.getString(\"items[2].links[0]\") = " + firstLink);

        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String, Object>> allLocations = jsonPath.getList("items");
        System.out.println(" There is  :  " + allLocations.size() + " locations in map");
        for (Map<String, Object> location : allLocations) {

            System.out.println(location);

        }

        System.out.println("====== FIRST LOCATION ======");
        //Map<String,Object> firstLocation = jsonPath.getMap("items[0]");
        System.out.println("FIRST LOCATION " + allLocations.get(0));

        System.out.println("====== GET FIRST LOCATION ID  ======");
        System.out.println("FIRST LOCATION ID: " + allLocations.get(0).get("location_id"));

        System.out.println("====== FIRST LOCATION country ID  ======");
        System.out.println("FIRST LOCATION ID: " + allLocations.get(0).get("country_id"));
        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println("Get LINKS : " + allLocations.get(0).get("links"));
        // we  want to get href from first location what we need to do?
        List<Map<String,Object>> links =(List<Map<String,Object>>)allLocations.get(0).get("links");
        System.out.println("links of map: "+links);
        System.out.println("links.get(\"href\") = " + links.get(0).get("href"));


    }
}
