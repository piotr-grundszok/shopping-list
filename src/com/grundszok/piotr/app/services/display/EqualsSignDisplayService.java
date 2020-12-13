package com.grundszok.piotr.app.services.display;

public class EqualsSignDisplayService implements DisplayService {
    @Override
    public void print(String message) {
        System.out.println("=================================");
        System.out.println(message);
        System.out.println("=================================");
    }
}
