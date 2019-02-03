package com.example.unittestingwithmockito.business;

import com.example.unittestingwithmockito.data.SomeDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//step 8: use @InjectMocks to inject the dataservice dependency in the class under test
@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTestC {

    //no need to use a setter in @Before to set the mock data service; use @InjectMocks instead
    @InjectMocks
    SomeBusinessImpl someBusiness = new SomeBusinessImpl();
    //use @Mock annotation instead of doing "... = mock(SomeDataService.class)"
    @Mock
    SomeDataService dataServiceMock;

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