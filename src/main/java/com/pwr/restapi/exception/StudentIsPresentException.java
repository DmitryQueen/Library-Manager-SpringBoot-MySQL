package com.pwr.restapi.exception;

public class StudentIsPresentException extends RuntimeException{

    public StudentIsPresentException(String message) {
        super(message);
    }

    public StudentIsPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
