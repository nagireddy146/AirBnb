package com.airBnb_application.firstproject.Exception;

public class UnautorizedException extends RuntimeException{

    public UnautorizedException(String message) {
        super(message);
    }
}
