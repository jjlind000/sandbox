package com.company;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.*;

public class TestX {

    @Mock
    Y y;

    @InjectMocks
    X x=new X();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
    }

    // test the return of a value
    @Test
    public void testFoo() {
        X mockX=mock(X.class);
        when(mockX.foo()).thenReturn("bar","baz");
        String result = mockX.foo();
        verify(mockX,times(1)).foo();
        assertTrue("Verify foo returned bar", result.equals("bar"));
        assertEquals(mockX.foo(),"baz");
        System.out.println("done!");
    }

    // test the return of multiple values
    @Test
    public void testMoreThanOneReturnValue()  {
        Iterator<String> i= mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result= i.next()+" "+i.next();
        //assert
        assertEquals("Mockito rocks", result);
    }

    // test the return of a value based on the type of the provided parameter
    @Test
    public void testBar(){
        X mockX=mock(X.class);
        when(mockX.bar(isA(String.class))).thenReturn("String");
        assertEquals("String",mockX.bar("blah"));
    }

    //test that an exception is thrown when an illegal call is made
    @Test
    public void testException(){
        Properties properties = mock(Properties.class);
        when(properties.get("Anddroid")).thenThrow(new IllegalArgumentException());
        try {
            properties.get("Anddroid");
            fail("Anddroid is misspelled");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    //show the usage of doReturn(somevalue).when(someSpyObject).someMethod(someArgument)
    //this is used when someSpyObject.someMethod(someArgument) would result in an exception being thrown
    @Test
    public void testSpyMethodUsingDoReturn(){
        X spyX = spy(new X());
        doReturn("good").when(spyX).baz(-1);
        assertEquals("good", spyX.baz(-1));
    }

    //verify method is called with correct parameters
    @Test
    public void testMethodCalledWithCorrectParameters(){
        X mockX=mock(X.class);
        mockX.baz(10);
        verify(mockX).baz(ArgumentMatchers.eq(10));
    }

    //verify dependent method is called with correct parameter
    @Test
    public void testDependentMethodCalledWithCorrectParameters(){
        ArgumentCaptor<Integer> doubleItCaptor = ArgumentCaptor.forClass(Integer.class);
        x.f1(10);
        verify(y).doubleIt(doubleItCaptor.capture());
        assertEquals(Integer.valueOf(10), (Integer.valueOf(doubleItCaptor.getValue())));

        when(y.doubleIt(20)).thenReturn(40);
        assertEquals(40, x.f1(20));

        reset(y);
        x.f1(10);
        x.f1(20);
        verify(y,times(2)).doubleIt(or(eq(10),eq(30)));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<String> list = mock(List.class);
        when(list.size()).thenThrow(new RuntimeException("size() method not supported"));

        //junit5: Exception exception = assertThrows(RuntimeException.class, () -> list.size());
        //assertEquals("size() method not supported", exception.getMessage());
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("size() method not supported");
        list.size();
    }



}