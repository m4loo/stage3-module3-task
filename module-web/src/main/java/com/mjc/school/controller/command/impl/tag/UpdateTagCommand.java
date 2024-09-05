package com.mjc.school.controller.command.impl.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class UpdateTagCommand extends AbstractCommand<TagDtoRequest, TagDtoResponse, Long> implements Command {
    public UpdateTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        TagDtoRequest tagDtoRequest = new TagDtoRequest();
        System.out.println(MenuConstants.ENTER_TAG_ID);
        Long id = Utils.inputLongNumber(getInput());
        System.out.println(MenuConstants.ENTER_TAG);
        String name = getInput().nextLine();
        tagDtoRequest.setId(id);
        tagDtoRequest.setName(name);
        System.out.println(getController().update(tagDtoRequest));
    }
}
