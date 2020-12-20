package com.grundszok.piotr.app.controllers;

import com.grundszok.piotr.app.DisplayServiceFactory;
import com.grundszok.piotr.app.exceptions.EmptyShoppingListException;
import com.grundszok.piotr.app.model.Item;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.PersistanceService;
import com.grundszok.piotr.app.services.display.DisplayService;

import java.util.List;
import java.util.stream.Collectors;

import static com.grundszok.piotr.app.messages.Messages.INVALID_LIST;

public class MenuController {

    DisplayService displayService;
    InputService inputService;
    PersistanceService persistanceService;

    List<Item> shoppingList;

    public MenuController(DisplayService displayService, InputService inputService, PersistanceService persistanceService) {
        this.displayService = displayService;
        this.inputService = inputService;
        this.persistanceService = persistanceService;
    }

    private void setDisplayService(DisplayService displayService) {
        this.displayService = displayService;
        inputService.setDisplayService(displayService);
    }

    public void chooseDisplayStrategyFromUserInput() {
        String msg = "Witaj!\n" +
                "Wybierz ustawienie wyswietlania\n" +
                "Dostepne opcje: + , = , *\n" +
                "Zeby wyjsc wproadz: X";

        displayService.print(msg);
        char displayCharFromUser = inputService.getDisplayCharFromUser();
        while (displayCharFromUser != 'X') {
            try {
                DisplayService displayService = DisplayServiceFactory.produceDisplayServiceFromChar(displayCharFromUser);
                setDisplayService(displayService);
            } catch (IllegalArgumentException exception) {
                displayService.print("Unknown character for display type");
            }

            displayService.print(msg);
            displayCharFromUser = inputService.getDisplayCharFromUser();
        }
    }

    public void print(String msg) {
        displayService.print(msg);
    }

    public void start() {
        while (true) {
            printMenu();
            final boolean listValid = shoppingList == null || shoppingList.isEmpty();
            switch (inputService.getUserChoice()) {
                case "1" -> {
                    displayService.print("Podaj pierwszy produkt:");
                    this.shoppingList = inputService.getShopItemsFromUser(this.shoppingList);
                }
                case "2" -> {
                    if (listValid) {
                        displayService.print(INVALID_LIST);
                        break;
                    }

                    String productCollection = this.shoppingList.stream().map(Object::toString).collect(Collectors.joining("\n"));
                    displayService.print(productCollection);
                    displayService.printWithoutStyle("\n\nWprowadz dowolny przycisk zeby przejsc do menu");
                    inputService.getUserChoice();
                }
                case "3" -> {
                    if (listValid) {
                        displayService.print(INVALID_LIST);
                        break;
                    }
                    displayService.print("Czy jestes pewien ze chcesz wyczyscic liste zakupow? Jesli tak, wpisz \"3\"");
                    if (inputService.getUserChoice().equals("3")) {
                        this.shoppingList.clear();
                        displayService.print("Wyczyszczono liste zakupów");
                    }
                }
                case "4" -> chooseDisplayStrategyFromUserInput();
                case "5" -> {
                    try {
                        persistanceService.save(shoppingList);
                        displayService.print(String.format("Zapisano %d elementów do pliku", shoppingList.size()));
                    } catch (EmptyShoppingListException exception) {
                        displayService.print(exception.getMessage());
                    }
                }
                case "X" -> System.exit(0);
            }
        }
    }

    private void printMenu() {
        displayService.print("1. Stworz liste zakupow\n"
                + "2. Wyświetl liste zakupow\n"
                + "3. Wyczyść liste zakupow\n"
                + "4. Zmien styl wyświetlania\n"
                + "5. Zapisz listę zakupow do pliku\n\n"
                + "X. Zakoncz program");
    }
}
