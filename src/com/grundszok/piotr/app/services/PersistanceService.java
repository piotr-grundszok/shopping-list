package com.grundszok.piotr.app.services;

import com.grundszok.piotr.app.exceptions.EmptyShoppingListException;
import com.grundszok.piotr.app.model.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersistanceService {
    public void save(List<Item> shoppingList) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        try (FileWriter fileWriter = new FileWriter("ShoppingList_" + LocalDateTime.now().format(dateTimeFormatter) + ".txt")) {
            for (Item item : shoppingList
            ) {
                fileWriter.write(item.toString());
                fileWriter.write('\n');
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (NullPointerException exception) {
            throw new EmptyShoppingListException("Lista jest pusta!", exception);
        }
    }
}

