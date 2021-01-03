package com.grundszok.piotr.app;

import com.grundszok.piotr.app.controllers.MenuController;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.PersistenceService;
import com.grundszok.piotr.app.services.display.AstrixDisplayService;
import com.grundszok.piotr.app.services.display.DisplayService;

class App {
    public static void main(String[] args) {
        DisplayService displayService = new AstrixDisplayService();
        MenuController menuController = new MenuController(
                displayService,
                new InputService(displayService),
                new PersistenceService());

        menuController.start();
    }
}