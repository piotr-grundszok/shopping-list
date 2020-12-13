package com.grundszok.piotr.app.services.display;

import java.io.Serializable;

public class EqualsSignDisplayService implements DisplayService {
    @Override
    public void print(String message) {
        System.out.println("=================================");
        System.out.println(message);
        System.out.println("=================================");
    }

    }
