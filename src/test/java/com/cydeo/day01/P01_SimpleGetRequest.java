package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SimpleGetRequest {

    String url = "http://18.209.59.60:8000/api/spartans";
    /*
    when users send request to /api/spartans endPoint
    Then users should be able to see status cod is 200
    and print out response body into screen
     */
    @Test
    public void test1() {
       Response response = RestAssured.get(url);
        System.out.println("Status code:" +response.statusCode());// or we can use below
        System.out.println("Status code:" +response.getStatusCode());

        // verify that the status code is 200
      int actualStatus = response.getStatusCode();
      //Assert that is 200
        Assertions.assertEquals(200,actualStatus);
        //print json response body
        response.prettyPrint();
    }
    
}
