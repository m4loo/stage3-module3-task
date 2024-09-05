package com.mjc.school.controller.command.impl.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.Scanner;

public class ReadNewsByIdCommand extends AbstractCommand<NewsDtoRequest, NewsDtoResponse, Long> implements Command {

    public ReadNewsByIdCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_NEWS_ID);
        System.out.println(getController().readById(Utils.inputLongNumber(getInput())));
    }
}
