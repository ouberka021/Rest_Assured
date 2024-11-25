package com.cydeo.Utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public abstract class BaseUtils {

    @BeforeAll
    public static void init() {
        // RestAssured.baseURI is used to initialize the base URI
        System.err.println("Initialization base URI");
        System.err.println("--------------------------------");
        RestAssured.baseURI = "http://18.209.59.60:8000";

    }


    @AfterAll
    public static void teardown() {
        // RestAssured.reset() is used to reset the RestAssured context after each test method.
        System.err.println("--------------------------------");
        System.err.println("RestAssured reseted successfully");
        RestAssured.reset();

    }


}
