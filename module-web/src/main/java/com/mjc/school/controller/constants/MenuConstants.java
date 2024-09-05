package com.mjc.school.controller.constants;

public class MenuConstants {
    private MenuConstants(){

    }
    public static final String START_MENU_TEXT =
            """
                    Enter the number of operation:
                    1 - Get all news.
                    2 - Get all authors.
                    3 - Get all tags.
                    4 - Get news by id.
                    5 - Get author by id.
                    6 - Get tag by id.
                    7 - Create news.
                    8 - Create author.
                    9 - Create tag.
                    10 - Update news.
                    11 - Update author.
                    12 - Update tag.
                    13 - Remove news by id.
                    14 - Remove author by id.                           
                    15 - Remove tag.
                    16 - Get Author by news id.
                    17 - Get Tags by news id.
                    18 - Get News by tag names, tag id, author name, title, content.
                    0 - Exit.
                    """;

    public static final String AUTHOR = "Author";
    public static final String NEWS = "News";

    public static final String TAG = "Tag";

    public static final String ENTER_NEWS_ID = "Enter news id:";
    public static final String ENTER_TITLE = "Enter title:";
    public static final String ENTER_CONTENT = "Enter content:";
    public static final String ENTER_AUTHOR_ID = "Enter author id:";
    public static final String ENTER_AUTHOR_NAME = "Enter author name:";
   public static final  String ENTER_TAG_ID= "Enter tag id:";
   public static final String ENTER_TAG = "Enter tag";
    public static final String OPERATION_GET_ALL = "Operation: Get all %s.";
    public static final String OPERATION_GET_BY_ID = "Operation: Get %s by id.";
    public static final String OPERATION_CREATE = "Operation: Create %s.";
    public static final String OPERATION_UPDATE = "Operation: Update %s.";
    public static final String OPERATION_DELETE = "Operation: Delete %s.";
    public static final String OPERATION_EXIT = "Bye!";
    public static final String OPERATION_ERROR = "Incorrect command!";
}
