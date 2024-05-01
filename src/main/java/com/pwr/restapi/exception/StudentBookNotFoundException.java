package com.pwr.restapi.exception;

public class StudentBookNotFoundException extends RuntimeException{

    public StudentBookNotFoundException(String message) {
        super(message);
    }

    public StudentBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
