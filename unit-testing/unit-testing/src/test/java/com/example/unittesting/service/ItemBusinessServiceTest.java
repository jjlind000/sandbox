package com.example.unittesting.service;

import com.example.unittesting.data.ItemRepository;
import com.example.unittesting.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //don't forget this!!!
public class ItemBusinessServiceTest {

    @InjectMocks
    ItemBusinessService itemBusinessService;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void testGetAllItems() {
        when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item(101,"Dummy1",4.50,2),
                                                                new Item(102,"Dummy2",5.50,5),
                                                                new Item(103,"Dummy3",6.50,10)));
        assertEquals(9.0, itemBusinessService.getAllItems().get(0).getValue(), 0.0);
    }
}