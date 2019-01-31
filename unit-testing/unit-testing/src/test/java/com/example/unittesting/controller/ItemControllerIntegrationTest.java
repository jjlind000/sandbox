package com.example.unittesting.controller;

import com.example.unittesting.data.ItemRepository;
import com.example.unittesting.model.Item;
import com.jayway.jsonpath.JsonPath;
//import org.json.JSONArray;
import net.minidev.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//The @SpringBootTest annotation causes spring to search the package hierarchy, starting from the current
//package, for a class/classes annotated with @SpringBootConfiguration (@SpringBootApplication includes @SBC)
//It then launches that entire SPA context, so everything gets launched
//Use a random port so as not to interfere with other tests running on a CI server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void testContextLoads() throws JSONException {
        when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item(101),new Item(102),new Item(103),new Item(104),new Item(105),new Item(106)));
        String response = this.restTemplate.getForObject("/allItems", String.class);
        JSONAssert.assertEquals("[{id:101},{id:102},{id:103},{id:104},{id:105},{id:106}]",response, false );
        JSONArray array = JsonPath.read(response, "$");
        assertEquals( 6, array.size());
    }
}
