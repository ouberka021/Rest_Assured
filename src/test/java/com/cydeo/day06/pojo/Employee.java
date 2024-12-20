package com.cydeo.day06.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
// TODO -- data jsonIgnoreProperty  of properties
@Data
@JsonIgnoreProperties(ignoreUnknown =true)
public class Employee {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private int salary;

    @JsonProperty("department_id")
    private int departmentId;
}