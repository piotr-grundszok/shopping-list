package com.grundszok.piotr.app.controllers;

import com.grundszok.piotr.app.DisplayServiceFactory;
import com.grundszok.piotr.app.model.Item;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.display.DisplayService;

import java.util.List;
import java.util.stream.Collectors;

import static com.grundszok.piotr.app.messages.Messages.INVALID_LIST;

public class MenuController {

    DisplayService displayService;
    InputService inputService;

    List<Item> shoppingList;

    public MenuController(DisplayService displayService, InputService inputService) {
        this.displayService = displayService;
        this.inputService = inputService;
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

        print(msg);
        char displayCharFromUser = inputService.getDisplayCharFromUser();
        while (displayCharFromUser != 'X') {
            try {
                DisplayService displayService = DisplayServiceFactory.produceDisplayServiceFromChar(displayCharFromUser);
                setDisplayService(displayService);
            } catch (IllegalArgumentException exception) {
                displayService.print("Unknown character for display type");
            }

            print(msg);
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
                    print("Podaj pierwszy produkt:");
                    this.shoppingList = inputService.getShopItemsFromUser(this.shoppingList);
                }
                case "2" -> {
                    if (listValid) {
                        print(INVALID_LIST);
                        break;
                    }

                    String productCollection = this.shoppingList.stream().map(Object::toString).collect(Collectors.joining("\n"));
                    print(productCollection);
                    displayService.printWithoutStyle("\n\nWprowadz dowolny przycisk zeby przejsc do menu");
                    inputService.getUserChoice();
                }
                case "3" -> {
                    if (listValid) {
                        print(INVALID_LIST);
                        break;
                    }
                    print("Czy jestes pewien ze chcesz wyczyscic liste zakupow? Jesli tak, wpisz \"3\"");
                    if (inputService.getUserChoice().equals("3")) {
                        this.shoppingList.clear();
                        print("Wyczyszczono liste zakupów");
                    }
                }
                case "4" -> chooseDisplayStrategyFromUserInput();
                case "X" -> System.exit(0);
            }
        }
    }

    private void printMenu() {
        print("1. Stworz liste zakupow\n"
                + "2. Wyświetl liste zakupow\n"
                + "3. Wyczyść liste zakupow\n"
                + "4. Zmien styl wyświetlania\n\n"
                + "X. Zakoncz program");
    }
}
