package com.pwr.restapi.exception;

public class BookIsBookedException extends RuntimeException{

    public BookIsBookedException(String message) {
        super(message);
    }

    public BookIsBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}
