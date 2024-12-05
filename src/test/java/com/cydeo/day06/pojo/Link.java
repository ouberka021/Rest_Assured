package com.cydeo.day06.pojo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
@Data
// instead to use all of @Getter, @Setter and @ToString use @Data
public class Link {
    private String href;
    private String rel;
}
