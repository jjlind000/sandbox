package com.example.unittesting.spike;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

    private static final String actualResponse = "{\"id\":1,\"name\":\"Dummy item\",\"price\":5.99,\"quantity\":10}";

    @Test
    public void testJsonAssertStrictTrue() throws JSONException {
        //You only need \" ... \" when there are spaces in the object name or value eg in "Dummy item":
        String expectedResponse = "{id:        1,name:\"Dummy item\",price:5.99,quantity:10}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void testJsonAssertStrictTrueJustId() throws JSONException {
        String expectedResponse = "{\"id\":        1}";
        JSONAssert.assertNotEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void testJsonAssertStrictFalseJustId() throws JSONException {
        String expectedResponse = "{\"id\":        1}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    public void testJsonAssertStrictFalse() throws JSONException {
        String expectedResponse = "{\"id\":        1,\"name\":\"Dummy item\",\"price\":5.99,\"quantity\":10}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

}
