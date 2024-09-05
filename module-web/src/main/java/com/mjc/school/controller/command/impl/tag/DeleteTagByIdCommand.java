package com.mjc.school.controller.command.impl.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class DeleteTagByIdCommand extends AbstractCommand<TagDtoRequest, TagDtoResponse, Long> implements Command {
    public DeleteTagByIdCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_TAG_ID);
        System.out.println(getController().deleteById(Utils.inputLongNumber(getInput())));
    }
}
