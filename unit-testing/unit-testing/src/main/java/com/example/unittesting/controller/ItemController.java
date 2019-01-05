package com.example.unittesting.controller;

import com.example.unittesting.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RestController
public class ItemController {
    //name of method doesn't matter
    @GetMapping(path="/item", produces=MediaType.APPLICATION_JSON)
    public Item getDummyItem(){
        return new Item(1,"Dummy item", 3.99, 10);
    }

    @GetMapping(path="/item2")
    //@javax.ws.rs.GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Item getDummyItem2(){
        return new Item(1,"Dummy item", 4.99, 10);
    }

    //Shows that Spring returns JSON by default - no need for @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(path="/item3")
    public Item getDummyItem3(){
        return new Item(1,"Dummy item", 5.99, 10);
    }
}
