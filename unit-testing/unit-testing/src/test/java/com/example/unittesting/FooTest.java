package com.example.unittesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jasonl on 03/01/19
 */
@RunWith(MockitoJUnitRunner.class) //NEEDED when we use @InjectMocks and @Mock annotations
public class FooTest
{
    //WITHOUT @InjectMocks and @Mock annotations:
    /*
    private Foo foo;
    DataService mockDataService = mock(DataServiceStub.class);

    @Before
    public void setUp() throws Exception
    {
        foo=new Foo();
        foo.setDataService(mockDataService);
    }
    */

    //USING @InjectMocks and @Mock annotations:
    @InjectMocks //@InjectMocks means: inject mock objects into this object using constructor injection, property injection, or field injection (in that order)
                 //Allows shorthand mock and spy injection. Minimizes repetitive mock and spy injection
    private Foo foo;

    @Mock
    X x;

    @Mock
    DataService mockDataService = new DataServiceStub();

/*  //Non-mocking way:
    @Test
    public void testCalculateSum()
    {
        int actualResult = foo.calculateSum(new int[] {1,2,3});
        int expectedResult = 6;
        assertEquals("testing sum...", expectedResult, actualResult);
    }

    @Test
    public void testCalculateSum_EmptyArray()
    {
        int actualResult = foo.calculateSum(new int[] {});
        int expectedResult = 0;
        assertEquals("testing sum...", expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingDataService()
    {
        foo.setDataService(new DataServiceStub());
        int actualResult = foo.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals("testing sum...", expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingEmptyArrayDataService()
    {
        foo.setDataService(new DataServiceEmptyArrayStub());
        int actualResult = foo.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals("testing sum...", expectedResult, actualResult);
    }
*/

    @Test
    public void calculateSumUsingMockDataService()
    {
        foo.printDataService();
        foo.getDataService().bar();
        when(mockDataService.retrieveAllData()).thenReturn(new int[]{1,2,3,4});
        assertEquals("testing sum...", 10, foo.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingEmptyArrayMockDataService()
    {

        when(mockDataService.retrieveAllData()).thenReturn(new int[]{});
        assertEquals("testing sum...", 0, foo.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingSingleElementMockDataService()
    {

        when(mockDataService.retrieveAllData()).thenReturn(new int[]{3});
        assertEquals("testing sum...", 3, foo.calculateSumUsingDataService());
    }
}