package com.grundszok.piotr.app.services;

import com.grundszok.piotr.app.model.Item;
import com.grundszok.piotr.app.services.display.DisplayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputService {
    final static char DEFAULT_STYLE = '*';


    DisplayService displayService;
    Scanner scanner = new Scanner(System.in);

    public List<Item> getShopItemsFromUser(List<Item> itemList) {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        while (true) {
            String itemName = scanner.nextLine();
            if (itemName.trim().toUpperCase().equals("X")) {
                break;
            }
            displayService.print("Podaj ilość:");

            String itemQuantity = scanner.nextLine();
            itemList.add(new Item(itemName, itemQuantity));
            displayService.print("Dodano " + itemName + ". \nPodaj kolejny produkt, albo wpisz \"X\" żeby wyjść:");
        }
        return itemList;
    }

    public char getDisplayCharFromUser() {
        String input = scanner.nextLine();

        return input.isEmpty()
                ? DEFAULT_STYLE
                : input.toCharArray()[0];
    }

    public String getUserChoice() {
        return scanner.nextLine();
    }

    public InputService(DisplayService displayService) {
        this.displayService = displayService;
    }

    public void setDisplayService(DisplayService displayService) {
        this.displayService = displayService;
    }
}
