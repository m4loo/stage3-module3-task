package com.mjc.school.service.exceptions;

import lombok.Getter;

@Getter
public class InputExceptions extends Exception{

private final String errorMessage;

    public InputExceptions (String errorInfo) {
        super (errorInfo);
        this.errorMessage = errorInfo;
    }
}