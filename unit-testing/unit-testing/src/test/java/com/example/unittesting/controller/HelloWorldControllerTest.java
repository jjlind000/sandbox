package com.example.unittesting.controller;

import org.junit.Before;
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
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCallHelloWorld() throws Exception {
        //call url "hello-world"
        RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"))
                .andReturn();
        assertEquals("Hello World!",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testCallUnmappedURL() throws Exception {
        //call url "foo-world"
        RequestBuilder request = MockMvcRequestBuilders.get("/foo-world").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().is(404))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals("",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testCallHelloWorldJSON() throws Exception {
        //call url "json/hello-world"
        RequestBuilder request = MockMvcRequestBuilders.get("/json/hello-world").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"response\":\"Hello World!\"}"))
                .andReturn();
        assertEquals("{\"response\":\"Hello World!\"}",mvcResult.getResponse().getContentAsString());
    }
}