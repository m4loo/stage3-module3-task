package com.mjc.school.controller.command.impl.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class CreateTagCommand extends AbstractCommand<TagDtoRequest, TagDtoResponse, Long> implements Command {
    public CreateTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_TAG);
        String name = getInput().nextLine();
        TagDtoRequest tagDtoRequest = new TagDtoRequest();
        tagDtoRequest.setName(name);
        System.out.println(getController().create(tagDtoRequest));

    }
}
