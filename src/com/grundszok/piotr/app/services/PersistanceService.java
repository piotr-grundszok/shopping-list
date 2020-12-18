package com.grundszok.piotr.app.services;

import com.grundszok.piotr.app.model.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PersistanceService {
    public void save(List<Item> shoppingList) {

        try (FileWriter fileWriter = new FileWriter("ShoppingList_" + LocalDateTime.now())) {
            for (Item item : shoppingList
            ) {
                fileWriter.write(item.toString());
                fileWriter.write('\n');
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void load() {

    }
}

