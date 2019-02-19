package com.example.unittestingwithmockito.business;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

//step 9: create simple mocked list; test multiple return values and specific argument matchers
public class ListMockTest {

    List mockList = Mockito.mock(List.class);
    List<String> mockStringList = Mockito.mock(List.class);

    @Test
    public void testSize(){
        when(mockList.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mockList.size());
        assertEquals(10, mockList.size());
    }

    @Test
    public void testParameters(){
        when(mockList.get(0)).thenReturn("foo");
        assertEquals("foo", mockList.get(0));
    }

    //step 10 - use generic matcher
    @Test
    public void testGenericParameters(){
        when(mockList.get(anyInt())).thenReturn("foo");
        assertEquals("foo", mockList.get(3));
    }

    //step 11 - verify invocations
    @Test
    public void verifyInvocations(){
        //SUT
        String value = mockStringList.get(0);
        verify(mockStringList).get(anyInt());
        verify(mockStringList, times(1)).get(anyInt());
        verify(mockStringList, atLeast(1)).get(anyInt());
        verify(mockStringList, never()).add(anyString());
    }

    //step 12 - verify argument being passed in invocation
    @Test
    public void testUsingArgumentCapture(){
        //SUT
        mockStringList.add("foo");
        //create captor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //verify add method is called and also capture the argument passed in the invocation
        verify(mockStringList).add(captor.capture());
        //verify value of argument
        assertEquals("foo", captor.getValue());

    }

    //step 13 - verify argument being passed in multiple invocations of a method
    @Test
    public void testUsingArgumentCaptureInMultipleInvocations(){
        //SUT
        mockStringList.add("foo");
        mockStringList.add("bar");
        //create captor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //verify add method is called and also capture the argument passed in the invocation
        verify(mockStringList, times(2)).add(captor.capture());
        //verify value of argument
        List<String> values = captor.getAllValues();
        assertEquals("foo", values.get(0));
        assertEquals("bar", values.get(1));
    }

    //step 14 - introduction to spying
    @Test
    public void spying(){
        List<String> listspy = spy(ArrayList.class);
        listspy.add("Test");
        System.out.println(listspy.size());
        listspy.add("Test1");
        listspy.add("Test2");
        System.out.println(listspy.size());
        when(listspy.size()).thenReturn(99);
        System.out.println(listspy.size());
        verify(listspy).add("Test2");
    }
}
