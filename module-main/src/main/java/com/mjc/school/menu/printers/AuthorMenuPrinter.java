package com.mjc.school.menu.printers;

import com.mjc.school.controller.commands.AuthorCommandHandler;
import com.mjc.school.controller.implementation.AuthorController;
import com.mjc.school.interfaces.BaseMenuPrinter;
import com.mjc.school.menu.Buttons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class AuthorMenuPrinter implements BaseMenuPrinter<AuthorController> {

    private final AuthorCommandHandler authorCommandHandler;

    @Override
    public void printReadAllMenu(AuthorController controller) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_ALL_AUTHORS.getButtonMessage());
        out.println(authorCommandHandler.handleCommand(controller, Buttons.READ_ALL_AUTHORS.name()));
    }

    @Override
    public void printReadByIdlMenu(AuthorController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_BY_ID_AUTHORS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_ID);
        authorCommandHandler.createRequest(input.nextLine(), null);
        out.println(authorCommandHandler.handleCommand(controller, Buttons.READ_BY_ID_AUTHORS.name()));
    }

    @Override
    public void printCreateMenu(AuthorController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.CREATE_AUTHOR.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_NAME);
        authorCommandHandler.createRequest(null, input.nextLine());
        out.println(authorCommandHandler.handleCommand(controller, Buttons.CREATE_AUTHOR.name()));
    }

    @Override
    public void printUpdateMenu(AuthorController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.UPDATE_AUTHOR.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_ID);
        String authorId = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_NAME);
        String name = input.nextLine();
        authorCommandHandler.createRequest(authorId, name);
        out.println(authorCommandHandler.handleCommand(controller, Buttons.UPDATE_AUTHOR.name()));
    }

    @Override
    public void printDeleteByIdMenu(AuthorController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.DELETE_BY_ID_AUTHOR.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_ID);
        authorCommandHandler.createRequest(input.nextLine(), null);
        out.println(authorCommandHandler.handleCommand(controller, Buttons.DELETE_BY_ID_AUTHOR.name()));
    }

    @Override
    public void getDefault(AuthorController controller) {
        authorCommandHandler.handleCommand(controller, "DEFAULT");
    }
}
