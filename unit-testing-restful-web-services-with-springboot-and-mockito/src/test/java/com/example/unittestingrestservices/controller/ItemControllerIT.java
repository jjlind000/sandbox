package com.example.unittestingrestservices.controller;

import com.example.unittestingrestservices.data.ItemRepository;
import com.example.unittestingrestservices.model.Item.Item;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations={"classpath:test-application.properties"})
public class ItemControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void contextLoads() throws JSONException {
        //do itemRepository mocking here..
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Item(1, "Ball", 10, 5)
                ,new Item(2, "Bearing", 20, 10)));

        String response = this.restTemplate.getForObject("/allItems", String.class);
        JSONAssert.assertEquals("[{id:1},{id:2}]", response, false);
    }
}
