package com.example.unittestingwithmockito.business;

import com.example.unittestingwithmockito.data.SomeDataService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//step 7B: refactor - remove duplication, pull some setup stuff into an @Before method
//         eliminate wasted declarations etc
public class SomeBusinessMockTestB {

    SomeBusinessImpl someBusiness = new SomeBusinessImpl();
    //no need to set the data service; use a mock instead
    SomeDataService dataServiceMock = mock(SomeDataService.class);

    @Before
    public void setup(){
        someBusiness.setSomeDataService(dataServiceMock);
    }

    @Test
    public void testBasic_calculateSumUsingDataService() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});
        assertEquals(6, someBusiness.calculateSumUsingDataService());
    }

    @Test
    public void testEmpty_calculateSum() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, someBusiness.calculateSumUsingDataService());
    }

    @Test
    public void testOneValue_calculateSum() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{3});
        int expectedResult = 3;
        assertEquals(3, someBusiness.calculateSumUsingDataService());
    }
}