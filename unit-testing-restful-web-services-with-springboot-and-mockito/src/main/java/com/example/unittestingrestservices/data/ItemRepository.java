package com.example.unittestingrestservices.data;

import com.example.unittestingrestservices.model.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {}
