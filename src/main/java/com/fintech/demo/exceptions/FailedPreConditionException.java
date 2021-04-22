package com.fintech.demo.exceptions;

public class FailedPreConditionException extends RuntimeException {
    public FailedPreConditionException(String message){
        super(message);
    }
}
