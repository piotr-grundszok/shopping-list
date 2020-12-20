package com.grundszok.piotr.app.exceptions;

public class EmptyShoppingListException extends RuntimeException {
    public EmptyShoppingListException(String message, Throwable cause) {
        super(message, cause);
    }
}
