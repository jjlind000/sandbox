package com.example.unittesting;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jasonl on 04/01/19
 */
public class ListMockTest
{
    private List list = mock(List.class);

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
    }


}
