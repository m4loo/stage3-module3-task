package com.mjc.school.service.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg){
        super(msg);
    }
}
