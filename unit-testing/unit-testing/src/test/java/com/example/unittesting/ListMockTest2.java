package com.example.unittesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by jasonl on 04/01/19
 */
@RunWith(MockitoJUnitRunner.class)
public class ListMockTest2
{
    //private List list = mock(List.class);
    @Mock
    private List<String> list;


    @Test
    public void testMultipleListSizes(){
        when(list.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, list.size());
        assertEquals(10, list.size());
    }

    @Test
    public void testGetElementByIndex(){
        when(list.get(3)).thenReturn("foobar");
        assertEquals("foobar", list.get(3));
        assertEquals(null, list.get(33));
    }

    @Test
    public void testGetElementByIndexWithAnyInt(){
        //when list.get is called with any argument that is an int, return "foobar":
        when(list.get(anyInt())).thenReturn("foobar");
        assertEquals("foobar", list.get(3));
        assertEquals("foobar", list.get(-100));
    }

    //Verify that a method has or has not been invoked n times:
    @Test
    public void verifyInvocation(){
        //in system/class under test:
        String value = null;
        value = list.get(0);
        value = list.get(1);


        //verify(object).get(index) just verifies that get(index) was called; it does not invoke in any way get(index):
        verify(list).get(0);
        verify(list).get(0);
        verify(list).get(0);
        verify(list).get(0);
        //verify(obj, times(n) means n and only n times:
        verify(list, times(2)).get(anyInt());
        verify(list, atLeast(2)).get(anyInt());
        verify(list, atMost(2)).get(anyInt());
        verify(list, never()).get(999);
    }


    //Test argument capture
    @Test
    public void testArgumentCapture(){
        //in system/class under test:
        list.add("foobar");

        //Create captor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //Pass captor to method being verified (list.add(string)):
        verify(list).add(captor.capture());
        //Verify:
        assertEquals("foobar",captor.getValue());
    }

    //Test argument capture for a method that is called multiple times
    @Test
    public void testArgumentCaptureOfMultipleInvocations(){
        //in system/class under test:
        list.add("foo");
        list.add("bar");

        //Create captor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //Pass captor to method being verified (list.add(string))
        //Note that we have to supply the times(n) argument to verify
        verify(list, times(2)).add(captor.capture());
        //Verify:
        List<String> strings = captor.getAllValues();
        assertEquals("foo",strings.get(0));
        assertEquals("bar",strings.get(1));
    }


    //Test using a real (i.e. non-mock) object
    /* Often you heard developers how to spy and mock in Mockito in unit test but what are the difference between spy and mock in Mockito API? Both can be used to mock methods or fields.
       The difference is that in mock, you are creating a complete mock or fake object while in spy, there is the real object and you just spying or stubbing specific methods of it.
       When using mock objects, the default behavior of the method when not stub is do nothing. Simple means, if its a void method, then it will do nothing when you call the method or if its a method with a return then it may return null, empty or the default value.
       While in spy objects, of course, since it is a real method, when you are not stubbing the method, then it will call the real method behavior.
       If you want to change and mock the method, then you need to stub it.
     */
    //Typical use case for spy: when you want to test the implementation of code in the object being spied on
    //Typical use case for mock: when you want to mock an object B that A is dependent on/calls but you are not interested in testing B; it's A that you want to test
    @Test
    public void spying(){



        ArrayList mockList = mock(ArrayList.class);
        //NOTE: mockList is a MOCK class; it is NOT a real class! All method invocations will return default values
        System.out.println(mockList.get(0)); //returns null
        System.out.println(mockList.size()); //returns 0
        mockList.add("foo");
        mockList.add("foo2");
        System.out.println(mockList.size()); //returns 0

        //create a spy
        ArrayList spyList = spy(ArrayList.class);
        //NOTE: spyList is a REAL class! All method invocations will invoke the actual code in the class
        /* would throw Exception:
        System.out.println(spyList.get(0));
         */
        spyList.add("foo");
        spyList.add("foo2");
        System.out.println(spyList.size()); //returns 0
        verify(spyList).add("foo");
        verify(spyList).add("foo2");
    }
}
