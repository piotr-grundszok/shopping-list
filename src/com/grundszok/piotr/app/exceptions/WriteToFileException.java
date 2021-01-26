package com.grundszok.piotr.app.exceptions;

public class WriteToFileException extends RuntimeException {
    public WriteToFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteToFileException(String message) {
        super(message);
    }
}
