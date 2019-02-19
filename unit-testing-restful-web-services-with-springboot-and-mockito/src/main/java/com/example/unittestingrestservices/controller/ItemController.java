package com.example.unittestingrestservices.controller;


import com.example.unittestingrestservices.model.Item.Item;
import com.example.unittestingrestservices.service.ItemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemBusinessService businessService;

    @GetMapping("/dummy-item")
    public Item dummyItem(){
        return new Item(1, "Ball", 10, 5);
    }

    @GetMapping("/itemFromBusinessService")
    public Item itemFromBusinessService(){
        return businessService.getHardcodedItem();
    }

    @GetMapping("/allItems")
    public List<Item> retrieveAllItems(){
        return businessService.retrieveAllItems();
    }

    @PostMapping("/createItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item){
        item.setValue(item.getQuantity()*item.getPrice());
        return businessService.create(item);
    }






}
