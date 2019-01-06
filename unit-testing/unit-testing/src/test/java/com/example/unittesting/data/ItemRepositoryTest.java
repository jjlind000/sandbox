package com.example.unittesting.data;

import com.example.unittesting.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//@DataJpaTest will cause the in-memory db to be launched and the table populated using data.sql
//NB it seems that wherever data.sql is found (i.e. on the classpath) it will be executed
//so that if you have a data.sql in both src/main/resources and src/test/resources, both will be run.
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void testFindAll(){
        List<Item> items = itemRepository.findAll();
        assertEquals(4, items.size());
    }

}