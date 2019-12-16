package com.market.repository;

import com.market.domain.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements Repository {
    private static final String REPOSITORY_FILE_NAME = "repository";
    private static final Repository INSTANCE = new InMemoryRepository();
    private Map<String, Item> itemsMap = new HashMap<>();

    InMemoryRepository(Map<String, Item> itemsMap) {
        this.itemsMap = itemsMap;
    }

    private InMemoryRepository() {
        File file = new File(
                getClass().getClassLoader().getResource(REPOSITORY_FILE_NAME).getFile()
        );
        try {
            try (FileReader reader = new FileReader(file);
                 BufferedReader br = new BufferedReader(reader)) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] splited = line.split(";");
                    Item item = new Item(splited[0], splited[1], Double.parseDouble(splited[2]));
                    itemsMap.put(item.getSku(), item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Repository getInstance() {
        return INSTANCE;
    }

    @Override
    public Item getItem(String sku) {
        return itemsMap.get(sku);
    }
}
