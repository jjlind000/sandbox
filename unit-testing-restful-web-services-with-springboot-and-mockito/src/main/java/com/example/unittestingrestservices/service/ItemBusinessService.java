package com.example.unittestingrestservices.service;

import com.example.unittestingrestservices.data.ItemRepository;
import com.example.unittestingrestservices.model.Item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemBusinessService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getHardcodedItem(){
        return new Item(1, "Ball", 10, 5);
    }

    public List<Item> retrieveAllItems(){
        List<Item> items = itemRepository.findAll();
        for(Item item : items){
            item.setValue(item.getPrice()*item.getQuantity());
        }
        return items;
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }
}
