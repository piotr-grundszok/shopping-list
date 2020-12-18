package com.grundszok.piotr.app;

import com.grundszok.piotr.app.controllers.MenuController;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.PersistanceService;
import com.grundszok.piotr.app.services.display.AstrixDisplayService;
import com.grundszok.piotr.app.services.display.DisplayService;

class App {
    public static void main(String[] args) {
        PersistanceService persistanceService = new PersistanceService();
        DisplayService displayService = new AstrixDisplayService();
        InputService inputService = new InputService(displayService);
        MenuController menuController = new MenuController(displayService, inputService, persistanceService);

        menuController.start();
    }
}