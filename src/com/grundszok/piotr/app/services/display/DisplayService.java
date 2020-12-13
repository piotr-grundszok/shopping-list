package com.grundszok.piotr.app.services.display;

public interface DisplayService {
    void print(String message);

    default void printWithoutStyle(String message) {
        System.out.println(message);
    }
}
