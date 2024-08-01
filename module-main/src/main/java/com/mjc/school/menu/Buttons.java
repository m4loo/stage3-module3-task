package com.mjc.school.menu;

import lombok.Getter;

@Getter
public enum Buttons {
    READ_ALL_NEWS(1, ConstantsString.READ_ALL),
    READ_BY_ID_NEWS(2, ConstantsString.READ_BY_ID),
    CREATE_NEWS(3, ConstantsString.CREATE),
    UPDATE_NEWS(4, ConstantsString.UPDATE),
    DELETE_BY_ID_NEWS(5, ConstantsString.DELETE_BY_ID),

    READ_ALL_AUTHORS(6, ConstantsString.READ_ALL),
    READ_BY_ID_AUTHORS(7, ConstantsString.READ_BY_ID),
    CREATE_AUTHOR(8, ConstantsString.CREATE),
    UPDATE_AUTHOR(9, ConstantsString.UPDATE),
    DELETE_BY_ID_AUTHOR(10, ConstantsString.DELETE_BY_ID),

    READ_ALL_TAGS(11, ConstantsString.READ_ALL),
    READ_BY_ID_TAGS(12, ConstantsString.READ_BY_ID),
    CREATE_TAG(13, ConstantsString.CREATE),
    UPDATE_TAG(14, ConstantsString.UPDATE),
    DELETE_BY_ID_TAG(15, ConstantsString.DELETE_BY_ID),

    EXIT(0, "Exit.");

    private final String message;
    private final String buttonMessage;
    private final String entity = "{entity}";

    Buttons(int buttonId, String buttonMessage) {
        if (buttonId >= 1 && buttonId <= 5) {
            this.message = buttonId + " - "
                    + buttonMessage.replace(entity, "news");
            this.buttonMessage = buttonMessage.replace(entity, "news");
        } else if (buttonId == 6) {
            this.message = buttonId + " - "
                    + buttonMessage.replace(entity, "authors");
            this.buttonMessage = buttonMessage.replace(entity, "authors");
        } else if (buttonId > 6 && buttonId <= 10) {
            this.message = buttonId + " - "
                    + buttonMessage.replace(entity, "author");
            this.buttonMessage = buttonMessage.replace(entity, "author");
        } else if (buttonId == 11) {
            this.message = buttonId + " - "
                    + buttonMessage.replace(entity, "tags");
            this.buttonMessage = buttonMessage.replace(entity, "tags");
        } else if (buttonId > 11 && buttonId <= 15) {
            this.message = buttonId + " - "
                    + buttonMessage.replace(entity, "tag");
            this.buttonMessage = buttonMessage.replace(entity, "tag");
        } else {
            this.message = buttonId + " - " + buttonMessage;
            this.buttonMessage = buttonMessage;
        }
    }


    public static final class ConstantsString {

        private ConstantsString() {}

        public static final String READ_ALL = "Get all {entity}.";
        public static final String READ_BY_ID = "Get {entity} by id.";
        public static final String CREATE = "Create {entity}.";
        public static final String UPDATE = "Update {entity}.";
        public static final String DELETE_BY_ID = "Remove {entity} by id.";

        public static final String MAIN_MENU_TITLE = "Enter the number of operation:";
        public static final String OPERATIONS = "Operation: ";

        public static final String ENTER_NEWS_ID = "Enter news id:";
        public static final String ENTER_NEWS_TITLE = "Enter news title:";
        public static final String ENTER_NEWS_CONTENT = "Enter news content:";

        public static final String ENTER_AUTHOR_ID = "Enter author id:";
        public static final String ENTER_AUTHOR_NAME = "Enter author name:";

        public static final String TAG_ENTER_QUESTION = "Do you want to add tags to this news?\ny - yes\neverything else - no";
        public static final String TAG_ENTER_INFO = "You can add multiple tags\nEnter 'done' to complete";
        public static final String ENTER_TAG_ID = "Enter tag id:";
        public static final String ENTER_TAG_NAME = "Enter tag name:";
    }
}
