package com.example.unittesting.controller;

import com.example.unittesting.model.Item;
import com.example.unittesting.service.ItemBusinessService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@TestPropertySource("classpath:test-application.properties")
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    ItemBusinessService itemBusinessService;

    @MockBean
    private ItemBusinessService itemBusinessService;

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testGetDummyItem() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Dummy item\",\"price\":3.99,\"quantity\":10,\"value\":0.0}"))
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

    @Test
    public void testGetItemFromItemService() throws Exception {
        //without this line we get the following error:
        //org.json.JSONException: Unparsable JSON string
        when(itemBusinessService.getHardCodedItem()).thenReturn(new Item(1,"Dummy item", 9.99, 20));

        RequestBuilder request = MockMvcRequestBuilders.get("/service/dummyItem")
                                                       //.accept(MediaType.APPLICATION_JSON);
                                                        ;
        MvcResult result = mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().json("{id:1,name:\"Dummy item\",price:9.99,quantity:20}"))
                    .andReturn();
    }

    @Test
    public void testRetrieveAllItems() throws Exception {
        //In the unit test we don't really want to talk to the business service; we want to stub the business service and make it
        //return whatever values we want to return and then check the json values.
        //So we will stub the itemBusinessService.getAllItems() method
        when(itemBusinessService.getAllItems()).thenReturn(
                Arrays.asList(new Item(101,"Dummy1",4.50,2),
                              new Item(102,"Dummy2",5.50,5),
                              new Item(103,"Dummy3",6.50,10)));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allItems");
        MvcResult result = mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(content().json("[{\"id\":101,\"name\":\"Dummy1\",\"price\":4.5,\"quantity\":2}" +
                                                         ",{\"id\":102,\"name\":\"Dummy2\",\"price\":5.5,\"quantity\":5}" +
                                                         ",{\"id\":103,\"name\":\"Dummy3\",\"price\":6.5,\"quantity\":10}]"))
                    .andReturn();
    }

    @Test
    public void testCreateItem() throws Exception
    {
        //when(itemBusinessService.createItem()).then
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/createItem")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "    \"id\": 106,\n" +
                            "    \"name\": \"Dummy6\",\n" +
                            "    \"price\": 4.5,\n" +
                            "    \"quantity\": 2,\n" +
                            "    \"value\": 0\n" +
                            "}")
                    .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                                  .andExpect(status().isCreated())
                                  .andReturn();

    }



}