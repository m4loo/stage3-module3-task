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

public class CreateNewsCommand extends AbstractCommand<NewsDtoRequest, NewsDtoResponse, Long> implements Command  {

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }
    @Override
    public void execute() {
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest();
        List<Long> listTags = new ArrayList<>();
        System.out.println(MenuConstants.ENTER_TITLE);
        String title = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_CONTENT);
        String content = getInput().nextLine();
        System.out.println(MenuConstants.ENTER_AUTHOR_ID);
        Long authorId = Utils.inputLongNumber(getInput());
        System.out.println(MenuConstants.ENTER_TAG_ID);
        Long tagId = Utils.inputLongNumber(getInput());
        listTags.add(tagId);
        newsDtoRequest.setTitle(title);
        newsDtoRequest.setContent(content);
        newsDtoRequest.setAuthorId(authorId);
        newsDtoRequest.setTagsId(listTags);
        System.out.println(getController().create(newsDtoRequest));
    }
}
