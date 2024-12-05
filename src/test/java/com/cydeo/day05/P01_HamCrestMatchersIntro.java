package com.cydeo.day05;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;

public class P01_HamCrestMatchersIntro {

    @Test
    public void task1() {
        // Hamcrest provides various matchers for different types of assertions
        // First one will take value to check
        // second will take another matchers to make it readable / to add new assert
        assertEquals(2, 1 + 1);
        assertThat(6 + 3, is(9)); //import static org.hamcrest.Matchers.*
        // HamcrestMatchers comes from RestAssured dependency
        // 2 import to get rid of class names , directly call assertThat and  matchers
        assertThat(6 + 3, is(equalTo(9)));
        assertThat(6 + 3, equalTo(9));
        /*
        is(someValue)
        is(equalTo(someValue))
        is(greaterThan(someValue))
        equalTo(someValue)
        all of them same in terms of assertion
         */

        assertThat(5 + 5, not(equalTo(9)));
        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(equalTo(9))));
        /*
         They are all the same for assertion
         */
        assertThat(5 + 5, is(greaterThan(9)));
        assertThat(5 + 5, greaterThan(9));
        assertThat(5 + 5, greaterThanOrEqualTo(10));

        // assert lastThan
        assertThat(5 + 5, lessThan(11));
        assertThat(5 + 5, lessThanOrEqualTo(10));
        assertThat(5 + 5, is(lessThan(11)));
    }

    @Test
    public void task2() {
        String msg = "Hello world!";
        assertThat(msg, equalTo("Hello world!"));
        assertThat(msg, is("Hello world!"));
        assertThat(msg, is(equalTo("Hello world!")));
        assertThat(msg, is(equalToIgnoringCase("hello world!")));


        assertThat(msg, startsWith("He"));
        assertThat(msg, endsWith("!"));
        assertThat(msg, containsString("world"));
        assertThat(msg, containsStringIgnoringCase("WOrld"));
        assertThat(msg, is(not("he")));
    }

    @Test
    public void task3() {

        List<Integer> numberList = Arrays.asList(1, 2, 3,33,44,55); // 6 elements
// how to check size of element list
        assertThat(numberList, hasSize(6));
        // how to check 33 is into the collection
        assertThat(numberList, hasItem(33));
        // how to check 33 and 44 are into the collection
        assertThat(numberList, hasItems(33, 44));
        // how to check 33,3 and 44 are into the collection in any order
        assertThat(numberList, hasItems(3,33,44));
       //Loop through each of the element and make sure they are matching with Matchers inside the everyItem
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));
        //the values matching with ordering exp 1234 ----> 1234
        assertThat(numberList,containsInRelativeOrder(1, 2, 3,33,44,55));
        assertThat(numberList,containsInRelativeOrder(1, 2,3));// I can us three numbers instead of 6
      assertThat(numberList,containsInAnyOrder(55,1,2,3,33,44));// the value position might be different






    }
}
