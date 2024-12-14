package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Address {
    private int addressId;
    private String city;
    private String state;
    private String street;
    private int zipCode;
}
