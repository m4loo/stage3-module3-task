package com.mjc.school.service.exception;

public enum ErrorCode {
    NOT_FOUND_DATA(Constants.ERROR_400,"%s with id %d does not exist."),
    VALIDATE_STRING_LENGTH(Constants.ERROR_404, "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALIDATE_STRING_NULL(Constants.ERROR_400, "%s can not be null. %s is null"),

    VALIDATE_NEGATIVE_OR_NULL_NUMBER(Constants.ERROR_404, "%s can not be null or less than 1. %s is: %s"),

    VALIDATE_INT_VALUE(Constants.ERROR_404,"%s should be number");


    private final String errorMessage;

    ErrorCode(String errorCode, String msg){
        this.errorMessage = "ERROR_CODE:" + errorCode + " ERROR_MESSAGE: " + msg;
    }

    public String getMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorMessage;
    }
    private static class Constants{
        private static final String ERROR_400 = "400";
        private static final String ERROR_404 = "404";
        private Constants() {}

    }

}
