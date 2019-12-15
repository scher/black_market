package com.market.repository;

import com.market.domain.Item;

import java.util.Map;

class InMemoryRepository implements Repository {
    private Map<String, Item> itemsMap;

    InMemoryRepository(Map<String, Item> itemsMap) {
        this.itemsMap = itemsMap;
    }

    @Override
    public Item getItem(String sku) {
        return itemsMap.get(sku);
    }
}
