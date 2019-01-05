package com.example.unittesting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {}

    @Test
    public void getDummyItem() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Dummy item\",\"price\":3.99,\"quantity\":10}"))
                //when you verify json using .json method, it's more "intelligent" eg doesn't check for strict string matching
                .andExpect(content().json("{\"id\":      1,\"name\":\"Dummy item\",\"price\":3.99,\"quantity\":10}"))
                //.json method also allows you to omit parts of the json object - eg verify just the quantity:
                //nb .json("foo") calls json(jsonContent, false) where false = strict:false
                //ultimately ->
                //String content = result.getResponse().getContentAsString();
                //jsonHelper.assertJsonEqual(jsonContent, content, strict);
                .andExpect(content().json("{\"quantity\":10}"))
                .andReturn();
    }
}