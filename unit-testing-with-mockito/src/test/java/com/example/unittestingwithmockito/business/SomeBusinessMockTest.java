package com.example.unittestingwithmockito.business;

import com.example.unittestingwithmockito.data.SomeDataService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//steps 6 and 7: write a unit test using mockito to test the SomeBusinessImpl methods that use the data service
public class SomeBusinessMockTest {

    //step 6: update this test to use a mock
    @Test
    public void testBasic_calculateSumUsingDataService() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //no need to set the data service; use a mock instead
        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});
        someBusiness.setSomeDataService(dataServiceMock);
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }
    //step 7: update the other tests to use mocks
    @Test
    public void testEmpty_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //no need to set the data service; use a mock instead
        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        someBusiness.setSomeDataService(dataServiceMock);
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }
    //step 5: update test to use stub
    @Test
    public void testOneValue_calculateSum() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        //no need to set the data service; use a mock instead
        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{3});
        someBusiness.setSomeDataService(dataServiceMock);
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult);
    }
}