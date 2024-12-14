package com.cydeo.day07;

import com.cydeo.Utilities.CydeoTestBase;
import com.cydeo.pojo.Student;
import com.cydeo.pojo.Student_List;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {
    @DisplayName("GET /student/2")
    @Test
    public void test() {
        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON)
                        .and()
                        .pathParam("id", 2)
                        .when().get("student/{id}")
                        .then()
                        // .log().body()
                        .statusCode(200).extract().jsonPath();

        // Deserialize to student class
      Student student = jsonPath.getObject("students[0]", Student.class);
        System.out.println(student);

        // Deserialize student name
        String studentFirstName = student.getFirstName();
        System.out.println("First name: " + studentFirstName);

        // Deserialize student email address
       String emailAddress = student.getContact().getEmailAddress();
        System.out.println("Email address: " + emailAddress);

        assertEquals("Mark", studentFirstName);
        assertEquals("mark@email.com", emailAddress);
        assertEquals(13, student.getBatch());
        assertEquals("math", student.getMajor());
        assertEquals("Cydeo", student.getCompany().getCompanyName());
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());


    }


    // Same example using Students with List
    @DisplayName("GET Students with List /student/2")
    @Test
    public void test1() {
        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON)
                        .and()
                        .pathParam("id", 2)
                        .when().get("student/{id}")
                        .then()
                        // .log().body()
                        .statusCode(200).extract().jsonPath();

        // Deserialize to student class
        Student_List studentList = jsonPath.getObject("", Student_List.class);
        System.out.println(studentList);
        // we deserialize everything to students class which is holding list of students
        Student student = studentList.getStudents().get(0);

        System.out.println("Student " + student);

        // Deserialize student name
        String studentFirstName = student.getFirstName();
        System.out.println("First name: " + studentFirstName);

        // Deserialize student email address
        String emailAddress = student.getContact().getEmailAddress();
        System.out.println("Email address: " + emailAddress);

        assertEquals("Mark", studentFirstName);
        assertEquals("mark@email.com", emailAddress);
        assertEquals(13, student.getBatch());
        assertEquals("math", student.getMajor());
        assertEquals("Cydeo", student.getCompany().getCompanyName());
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());


    }
}
