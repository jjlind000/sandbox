package com.example.unittestingwithmockito.business;

import org.junit.Test;

import static org.junit.Assert.*;

//Step 2: write v simple unit test class for SomeBusinessImpl
public class SomeBusinessTest {

    @Test
    public void testBasic_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int actualResult = someBusiness.calculateSum(new int[]{0,1,2,3,4});
        int expectedResult = 10;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEmpty_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int actualResult = someBusiness.calculateSum(new int[]{});
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testOneValue_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int actualResult = someBusiness.calculateSum(new int[]{3});
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult);
    }
}