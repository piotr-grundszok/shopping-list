package com.grundszok.piotr.app.services;

import com.grundszok.piotr.app.model.Item;
import com.grundszok.piotr.app.services.display.DisplayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.grundszok.piotr.app.messages.Messages.*;

public class InputService {
    final static char DEFAULT_STYLE = '*';


    DisplayService displayService;
    Scanner scanner = new Scanner(System.in);

    public InputService(DisplayService displayService) {
        this.displayService = displayService;
    }

    public List<Item> getShopItemsFromUser(List<Item> itemList) {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        while (true) {
            String itemName = scanner.nextLine();
            if (itemName.trim().toUpperCase().equals("X")) {
                break;
            }
            displayService.print(GIVE_AMMOUNT);

            String itemQuantity = scanner.nextLine();
            itemList.add(new Item(itemName, itemQuantity));
            displayService.print(String.format(ANOTHER_PRODUCT, itemName));
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


    public String getApprovedUserChoice(Set<String> approvedInputSet, boolean caseInsensitive) {
        String dirtyUserInput = scanner.nextLine();
        if (caseInsensitive) {
            dirtyUserInput = dirtyUserInput.toUpperCase();
        }
        boolean isApproved = approvedInputSet.contains(dirtyUserInput);
        while (!isApproved) {
            displayService.printWithoutStyle(UNAPPROVED_USER_INPUT);
            dirtyUserInput = scanner.nextLine();
            isApproved = approvedInputSet.contains(dirtyUserInput);
        }
        return dirtyUserInput;
    }


    public String getApprovedUserChoice(Set<String> approvedInputSet) {
        return getApprovedUserChoice(approvedInputSet, true);
    }


    public void setDisplayService(DisplayService displayService) {
        this.displayService = displayService;
    }
}
