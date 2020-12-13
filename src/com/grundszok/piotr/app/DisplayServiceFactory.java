package com.grundszok.piotr.app;

import com.grundszok.piotr.app.services.display.AstrixDisplayService;
import com.grundszok.piotr.app.services.display.DisplayService;
import com.grundszok.piotr.app.services.display.EqualsSignDisplayService;
import com.grundszok.piotr.app.services.display.PlusSignDisplayService;

public class DisplayServiceFactory {

    //Prevent from being instantiated
    private DisplayServiceFactory() {
    }

    public static DisplayService produceDisplayServiceFromChar(char serviceStyleCharacter) {
        return switch (serviceStyleCharacter) {
            case '*' -> new AstrixDisplayService();
            case '+' -> new PlusSignDisplayService();
            case '=' -> new EqualsSignDisplayService();
            default -> throw new IllegalArgumentException();
        };
    }
}
