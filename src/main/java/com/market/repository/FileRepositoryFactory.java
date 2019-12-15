package com.market.repository;

import com.market.domain.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileRepositoryFactory extends AbstractRepositoryFactory {
    private static final String REPOSITORY_FILE_NAME = "repository";

    Repository initRepository() {
        Map<String, Item> itemsMap = new HashMap<>();
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
        return new InMemoryRepository(itemsMap);
    }
}
