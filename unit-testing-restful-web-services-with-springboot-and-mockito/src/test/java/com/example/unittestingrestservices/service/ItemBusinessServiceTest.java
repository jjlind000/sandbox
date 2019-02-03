package com.example.unittestingrestservices.service;

import com.example.unittestingrestservices.data.ItemRepository;
import com.example.unittestingrestservices.model.Item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemBusinessServiceTest {

    @Mock
    ItemRepository itemRepositoryMock;

    @InjectMocks
    ItemBusinessService itemBusinessService;

    @Test
    public void testRetrieveAllItems() {
        when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(
                new Item(1, "Ball", 10, 5)
                ,new Item(2, "Bearing", 20, 10)));

        List<Item> items = itemBusinessService.retrieveAllItems();
        assertEquals(50, items.get(0).getValue());
        assertEquals(200, items.get(1).getValue());
    }
}