package com.example.unittesting.service;

import com.example.unittesting.data.ItemRepository;
import com.example.unittesting.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
//import java.util.logging.Logger;

@Component //needed if elsewhere an instance of ItemBusinessService is injected using Autowiring
public class ItemBusinessService {

    @Autowired
    private ItemRepository itemRepository;

    Logger log = LoggerFactory.getLogger("testlogger");


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

    public Item createItem(Item item){
        log.info("creating item " + item);
        Item savedItem=itemRepository.save(item);
        savedItem.setValue(savedItem.getPrice()*savedItem.getQuantity()*10);
        return savedItem;
    }
}
