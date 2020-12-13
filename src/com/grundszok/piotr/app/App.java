package com.grundszok.piotr.app;

import com.grundszok.piotr.app.controllers.MenuController;
import com.grundszok.piotr.app.services.display.*;
import com.grundszok.piotr.app.services.InputService;
import com.grundszok.piotr.app.services.PersistanceService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class App {

    static int a;
    static boolean booleanDupy;

    public static void main(String[] args) {
        DisplayService displayService = new AstrixDisplayService();
        InputService inputService = new InputService(displayService);
        MenuController menuController = new MenuController(displayService, inputService);

     //   menuController.start();

        "cokolwiek".concat("cosinnego");


        Integer x = null;
//        NO CAN DO CAPTAIN
//        int y = null;

        Integer a = 5;
        Integer b = 5;

        Math.max(a, b);


        List<Integer> integerList = new ArrayList<>();
        int domyslnyxD;
        int c = 1;
        int d = 2;
        int e = 3;
        integerList.add(c);
        integerList.add(d);
        integerList.add(e);

        System.out.println(App.a);
        System.out.println(App.booleanDupy);


    }
}