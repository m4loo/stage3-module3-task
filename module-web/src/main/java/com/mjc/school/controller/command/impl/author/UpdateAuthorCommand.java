package com.mjc.school.controller.command.impl.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import java.util.Scanner;

public class UpdateAuthorCommand extends AbstractCommand<AuthorDtoRequest, AuthorDtoResponse, Long> implements Command {
    public UpdateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        AuthorDtoRequest authorRequest = new AuthorDtoRequest();
        System.out.println(MenuConstants.ENTER_AUTHOR_ID);
        Long id = Utils.inputLongNumber(getInput());
        System.out.println(MenuConstants.ENTER_AUTHOR_NAME);
        String name = getInput().nextLine();
        authorRequest.setId(id);
        authorRequest.setName(name);
        System.out.println(getController().update(authorRequest));

    }
}
