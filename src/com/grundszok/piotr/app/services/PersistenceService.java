package com.grundszok.piotr.app.services;

import com.grundszok.piotr.app.exceptions.EmptyShoppingListException;
import com.grundszok.piotr.app.exceptions.WriteToFileException;
import com.grundszok.piotr.app.messages.Messages;
import com.grundszok.piotr.app.model.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.grundszok.piotr.app.messages.Messages.INVALID_LIST;

public class PersistenceService {
    public static final String FILENAME = "ShoppingList_";
    public static final String FILE_EXTENSION = ".txt";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");

    public void save(List<Item> shoppingList) {
        final String currentTime = LocalDateTime.now().format(dateTimeFormatter);
        try (FileWriter fileWriter = new FileWriter(FILENAME + currentTime + FILE_EXTENSION)) {
            for (Item item : shoppingList) {
                fileWriter.write(item.toString() + "\n");
            }
        } catch (IOException exception) {
            throw new WriteToFileException(Messages.SAVING_ERROR, exception);
        } catch (NullPointerException exception) {
            throw new EmptyShoppingListException(INVALID_LIST, exception);
        }
    }
}

