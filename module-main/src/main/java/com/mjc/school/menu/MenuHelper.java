package com.mjc.school.menu;

import com.mjc.school.controller.implementation.AuthorController;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.menu.printers.AuthorMenuPrinter;
import com.mjc.school.menu.printers.NewsMenuPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class MenuHelper {
    private final NewsMenuPrinter newsMenuPrinter;
    private final AuthorMenuPrinter authorMenuPrinter;

    private final NewsController newsController;
    private final AuthorController authorController;

    public void printMainMenu() {
        while (true) {
            out.println(Buttons.ConstantsString.MAIN_MENU_TITLE);
            for (Buttons buttons : Buttons.values()) {
                out.println(buttons.getMessage());
            }
            Scanner input = new Scanner(in);
            switch (input.nextLine()) {
                case "1" -> newsMenuPrinter.printReadAllMenu(newsController);
                case "2" -> newsMenuPrinter.printReadByIdlMenu(newsController, input);
                case "3" -> newsMenuPrinter.printCreateMenu(newsController, input);
                case "4" -> newsMenuPrinter.printUpdateMenu(newsController, input);
                case "5" -> newsMenuPrinter.printDeleteByIdMenu(newsController, input);
                case "6" -> authorMenuPrinter.printReadAllMenu(authorController);
                case "7" -> authorMenuPrinter.printReadByIdlMenu(authorController, input);
                case "8" -> authorMenuPrinter.printCreateMenu(authorController, input);
                case "9" -> authorMenuPrinter.printUpdateMenu(authorController, input);
                case "10" -> authorMenuPrinter.printDeleteByIdMenu(authorController, input);
                case "0" -> System.exit(0);
                default -> newsMenuPrinter.getDefault(newsController);
            }
        }
    }
}
