package com.market.repository;

import com.market.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class InMemoryRepositoryTest {

    @Test
    void getItem() {
        HashMap<String, Item> itemsMap = new HashMap<>();
        Item expected = new Item("ipd", "Ipad", 123);
        itemsMap.put("ipd", expected);
        InMemoryRepository classToTest = new InMemoryRepository(itemsMap);
        Item item = classToTest.getItem("ipd");
        Assertions.assertEquals(expected, item);
    }
}