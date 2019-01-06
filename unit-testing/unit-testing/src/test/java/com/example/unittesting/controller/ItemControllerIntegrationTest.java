package com.example.unittesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//The @SpringBootTest annotation causes spring to search the package hierarchy, starting from the current
//package, for a class/classes annotated with @SpringBootConfiguration (@SpringBootApplication includes @SBC)
//It then launches that entire SPA context, so everything gets launched
//Use a random port so as not to interfere with other tests running on a CI server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testContextLoads() throws JSONException {
        String response = this.restTemplate.getForObject("/allItems", String.class);
        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104}]",response, false );
    }
}
