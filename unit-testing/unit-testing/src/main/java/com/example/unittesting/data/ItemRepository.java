package com.example.unittesting.data;

import com.example.unittesting.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<ENTITYTYPE, ENTITYPKTYPE>
public interface ItemRepository extends JpaRepository<Item, Integer> {


}
