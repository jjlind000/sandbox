package com.example.unittestingwithmockito.business;

import com.example.unittestingwithmockito.data.SomeDataService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//step 4: create a dataservice stub which returns some fixed small set of data
class SomeDataServiceStub implements SomeDataService{
    @Override
    public int[] retrieveAllData() {
        return new int[]{1,2,3};
    }
}
//step 5: create another dataservice stub
class SomeDataServiceStubEmpty implements SomeDataService{
    @Override
    public int[] retrieveAllData() {
        return new int[]{};
    }
}
//step 5: create another dataservice stub
class SomeDataServiceStubOneValue implements SomeDataService{
    @Override
    public int[] retrieveAllData() {
        return new int[]{3};
    }
}

//step 4: write a unit test to test the SomeBusinessImpl method that uses the data service
public class SomeBusinessStubTest {

    @Test
    public void testBasic_calculateSumUsingDataService() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //need to set the data service; use the dataservice stub!
        someBusiness.setSomeDataService(new SomeDataServiceStub());
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }
//step 5: update test to use stub
    @Test
    public void testEmpty_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //need to set the data service; use the dataservice stub!
        someBusiness.setSomeDataService(new SomeDataServiceStubEmpty());
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }
//step 5: update test to use stub
    @Test
    public void testOneValue_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //need to set the data service; use the dataservice stub!
        someBusiness.setSomeDataService(new SomeDataServiceStubOneValue());
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult);
    }
}