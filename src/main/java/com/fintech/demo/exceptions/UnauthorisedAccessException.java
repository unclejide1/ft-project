package com.fintech.demo.exceptions;

public class UnauthorisedAccessException extends RuntimeException {
    public UnauthorisedAccessException(String message){
        super(message);
    }
}
