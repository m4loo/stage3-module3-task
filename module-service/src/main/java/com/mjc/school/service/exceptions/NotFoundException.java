package com.mjc.school.service.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {

    private final String errorMessage;

    public NotFoundException(String errorInfo) {
        super(errorInfo);
        this.errorMessage = errorInfo;
    }
}