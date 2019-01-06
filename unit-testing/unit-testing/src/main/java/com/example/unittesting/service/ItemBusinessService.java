package com.example.unittesting.service;

import com.example.unittesting.data.ItemRepository;
import com.example.unittesting.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //needed if elsewhere an instance of ItemBusinessService is injected using Autowiring
public class ItemBusinessService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getHardCodedItem(){
        return new Item(1,"Dummy item", 9.99, 10);
    }

    public List<Item> getAllItems(){
        List<Item> items = itemRepository.findAll();
        for(Item item: items){
            item.setValue(item.getPrice() * item.getQuantity());
        }
        return items;
    }
}
