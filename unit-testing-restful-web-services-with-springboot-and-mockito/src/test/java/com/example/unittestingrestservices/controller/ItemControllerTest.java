package com.example.unittestingrestservices.controller;

import com.example.unittestingrestservices.model.Item.Item;
import com.example.unittestingrestservices.service.ItemBusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessServiceMock;

    @Test
    public void dummyItem_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                //.andExpect(content().json(jsonContent))
                .andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":5,\"value\":0}"))
                .andExpect(content().json("{id:1" +
                                                      ",name:Ball" +
                                                      ",price:10" +
                                                      ",quantity:5}"))
                .andExpect(content().json("{price:10}"))
                .andReturn();
    }

    @Test
    public void itemFromBusinessService_basic() throws Exception {
        when(businessServiceMock.getHardcodedItem()).thenReturn(new Item(1, "Ball", 10, 5));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/itemFromBusinessService")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1" +
                        ",name:Ball" +
                        ",price:10" +
                        ",quantity:5}"))
                .andReturn();
    }

    @Test
    public void testRetrieveAllItemsFromBusinessService_basic() throws Exception {
        when(businessServiceMock.retrieveAllItems()).thenReturn(Arrays.asList(
                new Item(1, "Ball", 10, 5)
               ,new Item(2, "Bearing", 20, 10)));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/allItems")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1,name:Ball,price:10,quantity:5}" +
                                                      ",{id:2,name:Bearing,price:20,quantity:10}]"))
                .andReturn();
    }

    @Test
    public void createItem() throws Exception {
        when(businessServiceMock.create(any(Item.class))).thenReturn(
                new Item(1, "itemy", 11, 33));
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/createItem")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"itemy\", \"price\":11,\"quantity\": 33}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(postRequest)
                .andExpect(status().isCreated())
                .andExpect(content().json("{id:1,name:itemy,price:11,quantity:33,value:0}"))
                .andReturn();
    }
}