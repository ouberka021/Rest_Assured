package com.cydeo.day03;

import com.cydeo.Utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_HrWithResponsePath extends HrTestBase {

@DisplayName("Get request to countriess with response path")
    @Test
    public void test1() {
    Response response = RestAssured.given()
            .accept(ContentType.JSON)
            .and().queryParam("q","{\"region_id\":2}")
            .when()
            .get("/countries");
//print limit
    System.out.println("response.path(\"limit\") :"+response.path("limit"));

//print hasMore
    System.out.println("response.path(\"hasMore\") :"+response.path("hasMore"));

//print second country name
    System.out.println("response.path(\"items[1].country_name\") :"+response.path("items[1].country_name"));
//print 4th element country name
    System.out.println("response.path(\"items[4].country_name\") :"+response.path("items[3].country_name"));

//print 3th element href
    System.out.println("response.path(\"items[3].links[0].href\") :"+response.path("items[2].links[0].href"));
//get all countries names
    System.out.println("-----   all countries names -----");

    List <String> countries = response.path("items.country_name");
    for (String each : countries) {
        System.out.println("Each name : "+each.trim());
    }
    System.out.println("----- verify all region id is 2 ------");
    List<Integer> regionIds = response.path("items.region_id");
    System.err.println(regionIds+" ");

    for (Integer each : regionIds) {
        assertEquals(2, each);
    }
    // all regions where region_id = 2
  response.prettyPrint();
    }
}
