package com.cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        // RestAssured.baseURI is used to initialize the base URI
        System.err.println("Initialization base URI");
        System.err.println("--------------------------------");
        RestAssured.baseURI = "http://18.209.59.60:8000";

    }



}
