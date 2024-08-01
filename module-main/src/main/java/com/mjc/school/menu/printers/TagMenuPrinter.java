package com.mjc.school.menu.printers;

import com.mjc.school.controller.commands.TagCommandHandler;
import com.mjc.school.controller.implementation.TagController;
import com.mjc.school.interfaces.BaseMenuPrinter;
import com.mjc.school.menu.Buttons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class TagMenuPrinter implements BaseMenuPrinter<TagController> {

    private final TagCommandHandler tagCommandHandler;

    @Override
    public void printReadAllMenu(TagController controller) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_ALL_TAGS.getButtonMessage());
        out.println(tagCommandHandler.handleCommand(controller, Buttons.READ_ALL_TAGS.name()));
    }

    @Override
    public void printReadByIdlMenu(TagController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_BY_ID_TAGS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_TAG_ID);
        tagCommandHandler.createRequest(input.nextLine(), null);
        out.println(tagCommandHandler.handleCommand(controller, Buttons.READ_BY_ID_TAGS.name()));
    }

    @Override
    public void printCreateMenu(TagController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.CREATE_TAG.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_TAG_NAME);
        tagCommandHandler.createRequest(null, input.nextLine());
        out.println(tagCommandHandler.handleCommand(controller, Buttons.CREATE_TAG.name()));
    }

    @Override
    public void printUpdateMenu(TagController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.UPDATE_TAG.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_TAG_ID);
        String tagId = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_NAME);
        String tagName = input.nextLine();
        tagCommandHandler.createRequest(tagId, tagName);
        out.println(tagCommandHandler.handleCommand(controller, Buttons.UPDATE_TAG.name()));
    }

    @Override
    public void printDeleteByIdMenu(TagController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.DELETE_BY_ID_TAG.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_TAG_ID);
        tagCommandHandler.createRequest(input.nextLine(), null);
        out.println(tagCommandHandler.handleCommand(controller, Buttons.DELETE_BY_ID_TAG.name()));
    }

    @Override
    public void getDefault(TagController controller) {
        tagCommandHandler.handleCommand(controller, "DEFAULT");
    }
}
