package com.ataberkcanitez.enocajrtest.exceptions;

public class CustomerServiceException extends RuntimeException{

    private static final long serialVersionUID = 55512326123L;


    public CustomerServiceException( String message) {
        super(message);
    }
}
