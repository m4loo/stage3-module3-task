package com.mjc.school.service.exceptions;

import lombok.Getter;

public enum ExceptionService {

    ERROR_COMMAND_NOT_FOUND(Constants.ERROR_CODE_XXXX1, Constants.ERROR_COMMAND_NOT_FOUND),
    ERROR_ID_LENGTH(Constants.ERROR_CODE_XXX11, Constants.ERROR_ID_LENGTH),
    ERROR_CHAR_LENGTH(Constants.ERROR_CODE_XXX12, Constants.ERROR_CHAR_LENGTH),
    ERROR_FORMAT(Constants.ERROR_CODE_XXX21, Constants.ERROR_FORMAT),
    ERROR_NOT_EXIST(Constants.ERROR_CODE_XXX31, Constants.ERROR_NOT_EXIST);

    @Getter
    private final String errorInfo;
    private final String target = "{entity}";

    ExceptionService(String errorCode, String errorMessage) {
        this.errorInfo = Constants.ERROR_CODE + errorCode + " "
                + Constants.ERROR_MESSAGE + errorMessage;
    }

    public String getErrorInfo(String entity) {
        return this.errorInfo
                .replace(target, entity);
    }

    public String getErrorInfo(String entity, Long id) {
        return this.errorInfo
                .replace(target, entity)
                .replace("{id}", Long.toString(id));
    }

    public String getErrorInfo(String str, String entity, String object, int max) {
        return this.errorInfo
                .replace(target, entity)
                .replace("{object}", object)
                .replace("{max}", Integer.toString(max))
                .replace("{str}", str);
    }

    public static class Constants {

        private Constants() {}

        public static final String ERROR_CODE = "ERROR_CODE:";
        public static final String ERROR_MESSAGE = "ERROR_MESSAGE:";

        public static final String ERROR_COMMAND_NOT_FOUND = "Command not found.";

        public static final String ERROR_ID_LENGTH = "{entity} id can not be null or less than 1. News id is: {id}";
        public static final String ERROR_CHAR_LENGTH = "{entity} {object} can not be less than 5 and more than {max} symbols. {entity} {object} is: {str}";
        public static final String ERROR_FORMAT = "{entity} id should be number";
        public static final String ERROR_NOT_EXIST = "{entity} with id {id} does not exist.";

        public static final String AUTHOR = "Author";
        public static final String NEWS = "News";
        public static final String TAG = "Tag";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String NAME = "name";

        public static final String ERROR_CODE_XXXX1 = "XXXX1";
        public static final String ERROR_CODE_XXX11 = "XXX11";
        public static final String ERROR_CODE_XXX12 = "XXX12";
        public static final String ERROR_CODE_XXX21 = "XXX21";
        public static final String ERROR_CODE_XXX31 = "XXX31";
    }
}