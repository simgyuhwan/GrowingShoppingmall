package com.growing.sgh.common.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
