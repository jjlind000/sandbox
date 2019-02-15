package com.x;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by jasonl on 24/01/19
 */
public class TestMathUtils
{
    @Test
    public void test() {
        MathUtils mockMathUtils = mock(MathUtils.class);
        when(mockMathUtils.add(1, 1)).thenReturn(2);
        when(mockMathUtils.isInteger(anyString())).thenReturn(true);

        ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);

        assertEquals(2, mockMathUtils.add(1, 1));
        assertTrue(mockMathUtils.isInteger("1"));
        assertTrue(mockMathUtils.isInteger("999"));

        verify(mockMathUtils).add(acInteger.capture(), acInteger.capture());
        List allValues = acInteger.getAllValues();
        assertEquals(Arrays.asList(1, 1), allValues);

        verify(mockMathUtils, times(2)).isInteger(acString.capture());
        List allStringValues = acString.getAllValues();
        assertEquals(Arrays.asList("1", "999"), allStringValues);
    }

    @Test
    public void testUsingArgumentMatcher(){

        List<String> strings = new ArrayList<>();
        List<String> spyStrings = spy(strings);
        spyStrings.add("foo");
        //Verify
        verify(spyStrings).add(argThat(s->s.length()>2));

        //when - thenReturn
        when(spyStrings.size()).thenReturn(10);
        System.out.println("list size:"+spyStrings.size());

        doReturn(20).when(spyStrings).size();
        System.out.println("list size:"+spyStrings.size());

    }
}
