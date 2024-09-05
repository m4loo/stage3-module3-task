package com.mjc.school.controller.command.impl.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateNewsCommand extends AbstractCommand<NewsDtoRequest, NewsDtoResponse, Long> implements Command {

    public UpdateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest();
        List<Long> listId = new ArrayList<>();
        System.out.println(MenuConstants.ENTER_NEWS_ID);
        Long id = Utils.inputLongNumber(getInput());
        System.out.println(MenuConstants.ENTER_TITLE);
        String title = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_CONTENT);
        String content = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_AUTHOR_ID);
        Long authorId = Utils.inputLongNumber(getInput());
        System.out.println(MenuConstants.ENTER_TAG_ID);
        Long tagId = Utils.inputLongNumber(getInput());
        listId.add(tagId);
        newsDtoRequest.setId(id);
        newsDtoRequest.setTitle(title);
        newsDtoRequest.setContent(content);
        newsDtoRequest.setAuthorId(authorId);
        newsDtoRequest.setTagsId(listId);
        System.out.println(newsDtoRequest);
        System.out.println(getController().update(newsDtoRequest));

    }
}
