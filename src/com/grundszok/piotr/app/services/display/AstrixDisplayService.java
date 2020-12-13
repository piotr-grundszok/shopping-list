package com.grundszok.piotr.app.services.display;

public class AstrixDisplayService implements DisplayService {
    @Override
    public void print(String message) {
        final String border = "*********************************";
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }
}
