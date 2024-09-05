package com.mjc.school.controller.view;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Invoker;
import com.mjc.school.controller.command.impl.author.*;
import com.mjc.school.controller.command.impl.news.*;
import com.mjc.school.controller.command.impl.tag.*;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exception.InvalidDataException;
import com.mjc.school.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Invoker invoker;
    Scanner input = new Scanner(System.in);

    @Autowired
    public Menu(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController, BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, BaseController<TagDtoRequest, TagDtoResponse, Long> tagController, Invoker invoker) {
        this.authorController = authorController;
        this.newsController = newsController;
        this.tagController = tagController;
        this.invoker = invoker;
    }


    public void menuScreen() {

        while (true) {
            try {
                System.out.println(MenuConstants.START_MENU_TEXT);
                switch (input.nextLine()) {
                    case "1" -> invoker.setCommand(new ReadAllNewsCommand(newsController));
                    case "2" -> invoker.setCommand(new ReadAllAuthorsCommand(authorController));
                    case "3" -> invoker.setCommand(new ReadAllTagsCommand(tagController));
                    case "4" -> invoker.setCommand(new ReadNewsByIdCommand(newsController, input));
                    case "5" -> invoker.setCommand(new ReadAuthorByIdCommand(authorController, input));
                    case "6" -> invoker.setCommand(new ReadTagByIdCommand(tagController, input));
                    case "7" -> invoker.setCommand(new CreateNewsCommand(newsController, input));
                    case "8" -> invoker.setCommand(new CreateAuthorCommand(authorController, input));
                    case "9" -> invoker.setCommand(new CreateTagCommand(tagController, input));
                    case "10" -> invoker.setCommand(new UpdateNewsCommand(newsController, input));
                    case "11" -> invoker.setCommand(new UpdateAuthorCommand(authorController, input));
                    case "12" -> invoker.setCommand(new UpdateTagCommand(tagController, input));
                    case "13" -> invoker.setCommand(new DeleteNewsByIdCommand(newsController, input));
                    case "14" -> invoker.setCommand(new DeleteAuthorByIdCommand(authorController, input));
                    case "15" -> invoker.setCommand(new DeleteTagByIdCommand(tagController, input));
                    case "16" -> invoker.setCommand(new ReadAuthorByNewsId(authorController,input));
                    case "17" -> invoker.setCommand(new ReadTagsByNewsId(tagController, input));
                    case "18" -> invoker.setCommand(new ReadNewsByParam(newsController, input));
                    case "0" -> exitProgram();
                    default -> System.out.println(MenuConstants.OPERATION_ERROR);
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void exitProgram() {
        System.out.println(MenuConstants.OPERATION_EXIT);
        System.exit(0);
    }
}
