package com.grundszok.piotr.app.controllers;

import com.grundszok.piotr.app.DisplayServiceFactory;
import com.grundszok.piotr.app.exceptions.EmptyShoppingListException;
import com.grundszok.piotr.app.exceptions.WriteToFileException;
import com.grundszok.piotr.app.model.Item;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.PersistenceService;
import com.grundszok.piotr.app.services.display.DisplayService;

import java.util.List;
import java.util.stream.Collectors;

import static com.grundszok.piotr.app.messages.Messages.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;


public class MenuController {

    private final char closingCharacter = 'X';
    private final InputService inputService;
    private final PersistenceService persistenceService;
    private DisplayService displayService;

    private List<Item> shoppingList;

    private boolean run = true;

    public MenuController(DisplayService displayService, InputService inputService, PersistenceService persistenceService) {
        this.displayService = displayService;
        this.inputService = inputService;
        this.persistenceService = persistenceService;
    }

    private void setDisplayService(DisplayService displayService) {
        this.displayService = displayService;
        inputService.setDisplayService(displayService);
    }


    public void start() {
        while (run) {
            printMenu();
            switch (inputService.getUserChoice()) {
                case "1" -> createList();
                case "2" -> showList();
                case "3" -> clearList();
                case "4" -> changeDisplay();
                case "5" -> saveListToFile();
                case "x", "X" -> exit();
            }
        }
    }

    private void printMenu() {
        displayService.print(MENU_ITEMS);
    }

    private void createList() {
        displayService.print(FIRST_PRODUCT);
        this.shoppingList = inputService.getShopItemsFromUser(this.shoppingList);
    }

    private void showList() {
        if (validateList()) {
            String productCollection = this.shoppingList.stream().map(Object::toString).collect(Collectors.joining("\n"));
            displayService.print(productCollection);
            displayService.printWithoutStyle(NEXT_PRODUCT);
            inputService.getUserChoice();
        }
    }

    private void clearList() {
        if (validateList()) {
            displayService.print(format("%s \"%s\"", CONFIRM_CLEAR_LIST, closingCharacter));
            if (inputService.getUserChoice().equals(valueOf(closingCharacter))) {
                this.shoppingList.clear();
                displayService.print(CLEAR_CONFIRMED);
            }
        }
    }

    private void saveListToFile() {
        try {
            persistenceService.save(shoppingList);
            displayService.print(format(SAVED_TO_FILE_SIZE, shoppingList.size()));
        } catch (EmptyShoppingListException | WriteToFileException exception) {
            displayService.print(exception.getMessage());
        }
    }

    private void changeDisplay() {
        displayService.print(GREETING_MESSAGE + closingCharacter);
        char displayCharFromUser = inputService.getDisplayCharFromUser();
        while (displayCharFromUser != closingCharacter) {
            try {
                DisplayService displayService = DisplayServiceFactory.produceDisplayServiceFromChar(displayCharFromUser);
                setDisplayService(displayService);
            } catch (IllegalArgumentException exception) {
                displayService.print(INVALID_CHARACTER);
            }

            displayService.print(GREETING_MESSAGE);
            displayCharFromUser = inputService.getDisplayCharFromUser();
        }
    }

    private void exit() {
        displayService.print(GOODBYE);
        run = false;
    }

    private boolean validateList() {
        return validateList(true);
    }

    private boolean validateList(boolean withMessage) {
        final boolean listInvalid = shoppingList == null || shoppingList.isEmpty();
        if (listInvalid) {
            if (withMessage) {
                displayService.print(INVALID_LIST);
            }
            return false;
        }
        return true;
    }
}
