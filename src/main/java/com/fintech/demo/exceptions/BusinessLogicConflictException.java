package com.fintech.demo.exceptions;

public class BusinessLogicConflictException extends RuntimeException {
    public BusinessLogicConflictException(String message){
        super(message);
    }
}
