package com.mjc.school.controller.command.impl.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.AbstractCommand;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class ReadAuthorByNewsId  implements Command {
   private AuthorController authorController;
   private Scanner scanner;
    public ReadAuthorByNewsId(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController, Scanner scanner) {
        this.authorController = (AuthorController) authorController;
        this.scanner = scanner;
    }
    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_NEWS_ID);
        System.out.println(authorController.readByNewsId(Utils.inputLongNumber(scanner)));

    }
}
