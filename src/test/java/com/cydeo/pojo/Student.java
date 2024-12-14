package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private int studentId;
    private String admissionNo;
    private int batch;
    private String birthDate;
    private String firstName;
    private String gender;
    private String joinDate;
    private String lastName;
    private String major;
    private String password;
    private String section;
    private String subject;
    private Contact contact;
    private Company company;
}
