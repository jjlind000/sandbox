package com.example.unittestingrestservices.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloWorld_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                    .get("/hello-world")
                    .accept(MediaType.APPLICATION_JSON);
        //we use a MockMvc object to perform requests
        //call /hello-world and verify that the response contains "helloWorld"
        MvcResult result = mockMvc
                            .perform(request)
                            .andExpect(status().isOk())  //or status().is(200)
                            //.andExpect(content().json(jsonContent))
                            .andExpect(content().string("helloWorld"))
                            .andReturn();
        assertEquals("helloWorld", result.getResponse().getContentAsString());

    }
}