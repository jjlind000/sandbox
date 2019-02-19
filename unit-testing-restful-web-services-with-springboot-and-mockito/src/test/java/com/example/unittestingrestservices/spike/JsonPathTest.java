package com.example.unittestingrestservices.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {

    @Test
    public void JsonPathTest(){
        String response = "[{\"id\":1, \"name\": \"foo\", \"quantity\":5 }," +
                          "{\"id\":2, \"name\": \"bar\", \"quantity\":3 }," +
                          "{\"id\":3, \"name\": \"baz\", \"quantity\":2 }]";

        DocumentContext context = JsonPath.parse(response);
        int numberOfElementsInJsonArrayObject = context.read("$.length()");
        assertThat(numberOfElementsInJsonArrayObject).isEqualTo(3);
        System.out.println(context.read("$..id").toString()); //[1,2,3]
        JSONArray jsonArray = context.read("$..id");
        assertThat(jsonArray).hasSize(3);
        assertThat(context.read("$.[1]").toString()).isEqualTo("{id=2, name=bar, quantity=3}");
        assertThat(context.read("$.[?(@.name=='baz')]").toString())
                .isEqualTo("[{\"id\":3,\"name\":\"baz\",\"quantity\":2}]");
    }
}
